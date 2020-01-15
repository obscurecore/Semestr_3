package ru.potapov.Swing.Applications;

import java.awt.*;
import javax.swing.*;

import javax.swing.border.LineBorder;

import java.awt.image.BufferedImage;
import java.awt.geom.Path2D;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class Waveform
        implements ActionListener {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Waveform();
            }
        });
    }

    public static final int DEF_BUFFER_SAMPLE_SZ = 1024;

    public static final Color LIGHT_BLUE = new Color(128, 192, 255);
    public static final Color DARK_BLUE = new Color(0, 0, 127);
    public static boolean toolsBackGround = false;
    public static boolean bscale = false;

    public enum PlayStat {
        NO_FILE, PLAYING, PAUSED, STOPPED
    }

    public interface PlayerRef {
        public Object getLock();

        public PlayStat getStat();

        public File getFile();

        public void playbackEnded();

        public void drawDisplay(float[] samples, int svalid);
    }

    private JFrame mainFrame = new JFrame("Waveform Demo");
    private JPanel contentPane = new JPanel(new BorderLayout());
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    private Dimension dimension = toolkit.getScreenSize();


    private JLabel fileLabel = new JLabel("No file loaded");
    private DisplayPanel displayPanel = new DisplayPanel();
    private JToolBar playbackTools = new JToolBar();

    private ToolsButton bOpen = new ToolsButton("Open");
    private ToolsButton bChangeColor = new ToolsButton("Theme");
    private ToolsButton scale = new ToolsButton("Scale");
    private ToolsButton bPlay = new ToolsButton("Play");
    private ToolsButton bPause = new ToolsButton("Pause");
    private ToolsButton bStop = new ToolsButton("Stop");
    private ToolsButton bAbout = new ToolsButton("About");

    private File audioFile;
    private AudioFormat audioFormat;

    private final Object statLock = new Object();

    private volatile PlayStat playStat = PlayStat.NO_FILE;

    private final PlayerRef thisPlayer = new PlayerRef() {
        @Override
        public Object getLock() {
            return statLock;
        }

        @Override
        public PlayStat getStat() {
            return playStat;
        }

        @Override
        public File getFile() {
            return audioFile;
        }

        @Override
        public void playbackEnded() {
            synchronized (statLock) {
                playStat = PlayStat.STOPPED;
            }
            displayPanel.reset();
            displayPanel.repaint();
        }

        @Override
        public void drawDisplay(float[] samples, int svalid) {
            displayPanel.makePath(samples, svalid);
            displayPanel.repaint();
        }
    };

    public Waveform() {
        assert EventQueue.isDispatchThread();

        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                systemExit();
            }

        });

        playbackTools.setFloatable(false);
        playbackTools.add(bOpen);
        playbackTools.add(scale);
        playbackTools.add(bPlay);
        playbackTools.add(bPause);
        playbackTools.add(bStop);
        playbackTools.add(bChangeColor);
        playbackTools.add(bAbout);


        scale.addActionListener(this);
        bOpen.addActionListener(this);
        bAbout.addActionListener(this);
        bPlay.addActionListener(this);
        bPause.addActionListener(this);
        bStop.addActionListener(this);
        bChangeColor.addActionListener(this);


        fileLabel.setOpaque(true);
        fileLabel.setBackground(Color.BLACK);
        fileLabel.setForeground(Color.WHITE);
        fileLabel.setHorizontalAlignment(JLabel.CENTER);

        playbackTools.setBackground(Color.GRAY);
        playbackTools.setMargin(new Insets(0, 24, 0, 0));

        contentPane.add(fileLabel, BorderLayout.NORTH);
        contentPane.add(displayPanel, BorderLayout.CENTER);
        contentPane.add(playbackTools, BorderLayout.SOUTH);


        mainFrame.setContentPane(contentPane);

        mainFrame.pack();
        mainFrame.setResizable(true);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.setVisible(true);
    }

    private void systemExit() {
        boolean wasPlaying;
        synchronized (statLock) {
            if (wasPlaying = (playStat == PlayStat.PLAYING)) {
                playStat = PlayStat.STOPPED;
            }
        }

        mainFrame.setVisible(false);
        mainFrame.dispose();

        if (wasPlaying) {
            /*
             * helps prevent 'tearing' sound
             * if exit happens while during playback
             *
             */
            try {
                Thread.sleep(250L);
            } catch (InterruptedException ie) {
            }
        }

        System.exit(0);
    }

    private void loadAudio() {
        JFileChooser openDiag = new JFileChooser();

        if (JFileChooser.APPROVE_OPTION == openDiag.showOpenDialog(mainFrame)) {
            File selected = openDiag.getSelectedFile();

            try {

                AudioFileFormat fmt = AudioSystem.getAudioFileFormat(selected);

                audioFile = selected;
                audioFormat = fmt.getFormat();
                fileLabel.setText(audioFile.getName());
                playStat = PlayStat.STOPPED;

            } catch (IOException ioe) {
                showError(ioe);
            } catch (UnsupportedAudioFileException uafe) {
                showError(uafe);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if(source==bAbout){
            try {
                Desktop.getDesktop().browse(new URL("http://www.google.com").toURI());
            } catch (Exception e) {}
        }

        else if (source == bOpen) {
            synchronized (statLock) {
                if (playStat == PlayStat.PLAYING) {
                    playStat = PlayStat.STOPPED;
                }
            }

            loadAudio();
        } else if (source == scale) {
            mainFrame.add(new JTextField(20));
            JTextField jTextField = new JTextField();
            JTextArea jTextArea = new JTextArea(5, 20);
            jTextArea.setLineWrap(true);
            jTextArea.setText("heelolo");
            try {

                URL obj = new URL("http://mkyong.com");
                URLConnection conn = obj.openConnection();
                Map<String, java.util.List<String>> map = conn.getHeaderFields();

                jTextArea.setText("Printing Response Header...\n");

                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    jTextArea.append("Key : " + entry.getKey()
                            + " ,Value : " + entry.getValue());
                }

                jTextArea.append("\nGet Response Header By Key ...\n");
                String server = conn.getHeaderField("Server");

                if (server == null) {
                    jTextArea.setText("Key 'Server' is not found!");
                } else {
                    jTextArea.append("Server - " + server);
                }


                jTextArea.append("\n Done");



                JScrollPane jScrollPane = new JScrollPane(jTextArea);

                mainFrame.add(jScrollPane);
                mainFrame.revalidate();//redraw

            } catch (Exception e) {
                e.printStackTrace();
            }



        } else if (source == bPlay
                && audioFile != null
                && playStat != PlayStat.PLAYING) {

            synchronized (statLock) {
                switch (playStat) {

                    case STOPPED: {
                        playStat = PlayStat.PLAYING;
                        new PlaybackLoop(thisPlayer).execute();
                        break;
                    }

                    case PAUSED: {
                        playStat = PlayStat.PLAYING;
                        statLock.notifyAll();
                        break;
                    }
                }
            }

        } else if (source == bPause
                && playStat == PlayStat.PLAYING) {

            synchronized (statLock) {
                playStat = PlayStat.PAUSED;
            }

        } else if (source == bStop
                && (playStat == PlayStat.PLAYING || playStat == PlayStat.PAUSED)) {

            synchronized (statLock) {
                switch (playStat) {

                    case PAUSED: {
                        playStat = PlayStat.STOPPED;
                        statLock.notifyAll();
                        break;
                    }

                    case PLAYING: {
                        playStat = PlayStat.STOPPED;
                        break;
                    }
                }
            }
        } else if (source == bChangeColor) {
            if (toolsBackGround) {
                playbackTools.setBackground(Color.GRAY);
                toolsBackGround = false;

            } else {
                playbackTools.setBackground(Color.BLACK);
                toolsBackGround = true;
            }
        }

    }

    private static void showError(Throwable t) {
        JOptionPane.showMessageDialog(null,
                "Exception <" + t.getClass().getName() + ">" +
                        " with message '" + t.getMessage() + "'.",
                "There was an error",
                JOptionPane.WARNING_MESSAGE
        );
    }

    public static class PlaybackLoop extends SwingWorker<Void, Void> {

        private final PlayerRef playerRef;

        public PlaybackLoop(PlayerRef pr) {
            playerRef = pr;
        }

        @Override
        public Void doInBackground() {
            try {
                AudioInputStream in = null;
                SourceDataLine out = null;

                try {
                    try {
                        final AudioFormat audioFormat = (
                                AudioSystem.getAudioFileFormat(playerRef.getFile()).getFormat()
                        );

                        in = AudioSystem.getAudioInputStream(playerRef.getFile());
                        out = AudioSystem.getSourceDataLine(audioFormat);

                        final int normalBytes = normalBytesFromBits(audioFormat.getSampleSizeInBits());

                        float[] samples = new float[DEF_BUFFER_SAMPLE_SZ * audioFormat.getChannels()];
                        long[] transfer = new long[samples.length];
                        byte[] bytes = new byte[samples.length * normalBytes];

                        out.open(audioFormat, bytes.length);
                        out.start();


                        for (int feed = 0; feed < 6; feed++) {
                            out.write(bytes, 0, bytes.length);
                        }

                        int bread;

                        play_loop:
                        do {
                            while (playerRef.getStat() == PlayStat.PLAYING) {

                                if ((bread = in.read(bytes)) == -1) {

                                    break play_loop; // eof
                                }

                                samples = unpack(bytes, transfer, samples, bread, audioFormat);
                                samples = window(samples, bread / normalBytes, audioFormat);

                                playerRef.drawDisplay(samples, bread / normalBytes);

                                out.write(bytes, 0, bread);
                            }

                            if (playerRef.getStat() == PlayStat.PAUSED) {
                                out.flush();
                                try {
                                    synchronized (playerRef.getLock()) {
                                        playerRef.getLock().wait(1000L);
                                    }
                                } catch (InterruptedException ie) {
                                }
                                continue;
                            } else {
                                break;
                            }
                        } while (true);

                    } catch (UnsupportedAudioFileException uafe) {
                        showError(uafe);
                    } catch (LineUnavailableException lue) {
                        showError(lue);
                    }
                } finally {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                }
            } catch (IOException ioe) {
                showError(ioe);
            }

            return (Void) null;
        }

        @Override
        public void done() {
            playerRef.playbackEnded();

            try {
                get();
            } catch (InterruptedException io) {
            } catch (CancellationException ce) {
            } catch (ExecutionException ee) {
                showError(ee.getCause());
            }
        }
    }

    public static float[] unpack(
            byte[] bytes,
            long[] transfer,
            float[] samples,
            int bvalid,
            AudioFormat fmt
    ) {
        if (fmt.getEncoding() != AudioFormat.Encoding.PCM_SIGNED
                && fmt.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED) {

            return samples;
        }

        final int bitsPerSample = fmt.getSampleSizeInBits();
        final int bytesPerSample = bitsPerSample / 8;
        final int normalBytes = normalBytesFromBits(bitsPerSample);


        if (fmt.isBigEndian()) {
            for (int i = 0, k = 0, b; i < bvalid; i += normalBytes, k++) {
                transfer[k] = 0L;

                int least = i + normalBytes - 1;
                for (b = 0; b < normalBytes; b++) {
                    transfer[k] |= (bytes[least - b] & 0xffL) << (8 * b);
                }
            }
        } else {
            for (int i = 0, k = 0, b; i < bvalid; i += normalBytes, k++) {
                transfer[k] = 0L;

                for (b = 0; b < normalBytes; b++) {
                    transfer[k] |= (bytes[i + b] & 0xffL) << (8 * b);
                }
            }
        }

        final long fullScale = (long) Math.pow(2.0, bitsPerSample - 1);

        /*
         * the OR is not quite enough to convert,
         * the signage needs to be corrected.
         *
         */

        if (fmt.getEncoding() == AudioFormat.Encoding.PCM_SIGNED) {
//right left shift to the 64-bit


            final long signShift = 64L - bitsPerSample;

            for (int i = 0; i < transfer.length; i++) {
                transfer[i] = (
                        (transfer[i] << signShift) >> signShift
                );
            }
        } else {

            /*
             * subtract 2^(bits - 1) so the center is 0.
             */

            for (int i = 0; i < transfer.length; i++) {
                transfer[i] -= fullScale;
            }
        }

        //normalize to range of -1.0f to 1.0f

        for (int i = 0; i < transfer.length; i++) {
            samples[i] = (float) transfer[i] / (float) fullScale;
        }

        return samples;
    }

    public static float[] window(
            float[] samples,
            int svalid,
            AudioFormat fmt
    ) {
        /*
         * multiply the window against a sine curve, tapers ends
         *
         */

        int channels = fmt.getChannels();
        int slen = svalid / channels;

        for (int ch = 0, k, i; ch < channels; ch++) {
            for (i = ch, k = 0; i < svalid; i += channels) {
                samples[i] *= Math.sin(Math.PI * k++ / (slen - 1));
            }
        }

        return samples;
    }

    public static int normalBytesFromBits(int bitsPerSample) {

        /*
         * if format non-multiples of 8.
         *  bitsPerSample + 7 >> 3
         * computes a division of 8 rounding up.
         *
         */

        return bitsPerSample + 7 >> 3;
    }

    public class DisplayPanel extends JPanel {

        private final BufferedImage image;

        private final Path2D.Float[] paths = {
                new Path2D.Float(), new Path2D.Float(), new Path2D.Float()
        };

        private final Object pathLock = new Object();

        {
            Dimension pref = getPreferredSize();

            image = (
                    GraphicsEnvironment
                            .getLocalGraphicsEnvironment()
                            .getDefaultScreenDevice()
                            .getDefaultConfiguration()
                            .createCompatibleImage(
                                    pref.width, pref.height, Transparency.OPAQUE
                            )
            );
        }

        public DisplayPanel() {
            setOpaque(false);
        }

        public void reset() {
            Graphics2D g2d = image.createGraphics();
            g2d.setBackground(Color.BLACK);
            g2d.clearRect(0, 0, image.getWidth(), image.getHeight());
            g2d.dispose();
        }

        public void makePath(float[] samples, int svalid) {
            if (audioFormat == null) {
                return;
            }
            Path2D.Float current = paths[2];

            paths[2] = paths[1];
            paths[1] = paths[0];

            /*ratios*/

            float avg = 0f;
            float hd2 = getHeight() / 2f;

            final int channels = audioFormat.getChannels();

            /*
             * have to do a special op for the
             * 0th samples because moveTo.
             *
             */

            int i = 0;
            while (i < channels && i < svalid) {
                avg += samples[i++];
            }

            avg /= channels;

            current.reset();
            current.moveTo(0, hd2 - avg * hd2);

            int fvalid = svalid / channels;
            for (int ch, frame = 0; i < svalid; frame++) {
                avg = 0f;

                /* average the channels for each frame. */

                for (ch = 0; ch < channels; ch++) {
                    avg += samples[i++];
                }

                avg /= channels;

                current.lineTo(
                        (float) frame / fvalid * image.getWidth(), hd2 - avg * hd2
                );
            }

            paths[0] = current;

            Graphics2D g2d = image.createGraphics();

            synchronized (pathLock) {
               // g2d.setBackground(Color.BLACK);
                g2d.clearRect(0, 0, image.getWidth(), image.getHeight());

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );
                g2d.setRenderingHint(
                        RenderingHints.KEY_STROKE_CONTROL,
                        RenderingHints.VALUE_STROKE_PURE
                );

                g2d.setPaint(DARK_BLUE);
                g2d.draw(paths[2]);

                g2d.setPaint(LIGHT_BLUE);
                g2d.draw(paths[1]);

                g2d.setPaint(Color.WHITE);
                g2d.draw(paths[0]);
            }

            g2d.dispose();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            synchronized (pathLock) {
                g.drawImage(image, 0, 0, null);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(DEF_BUFFER_SAMPLE_SZ / 2, 128);
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();
        }
    }

    public static class ToolsButton
            extends JButton {
        public ToolsButton(String text) {
            super(text);

            setOpaque(true);
            setBorderPainted(true);
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);

            setBorder(new LineBorder(Color.GRAY) {
                @Override
                public Insets getBorderInsets(Component c) {
                    return new Insets(1, 4, 1, 4);
                }

                @Override
                public Insets getBorderInsets(Component c, Insets i) {
                    return getBorderInsets(c);
                }
            });

            Font font = getFont();
            setFont(font.deriveFont(font.getSize() - 1f));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        setForeground(LIGHT_BLUE);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        setForeground(Color.WHITE);
                    }
                }
            });
        }
    }

}