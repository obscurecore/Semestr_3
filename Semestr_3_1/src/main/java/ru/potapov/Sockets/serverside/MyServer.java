//package ru.potapov.Sockets.serverside;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class MyServer implements Runnable {
//    Socket socket;
//
//    public MyServer(Socket socket) {
//        this.socket = socket;
//    }
//
//    @Override
//    public void run() {
//
//        final void processError(HttpURLConnection connection){
//            try {
//                int code=socket.getResponseCode();
//                if (code == 200)     return;
//                URL url=connection.getURL();
//                String error=processError2_reason(connection);
//                if (code == 401) {
//                    if (error.contains("Basic authentication is not supported"))       throw new TwitterException.UpdateToOAuth();
//                    throw new TwitterException.E401(error + "\n" + url+ " ("+ (name == null ? "anonymous" : name)+ ")");
//                }
//                if (code == 403) {
//                    processError2_403(url,error);
//                }
//                if (code == 404) {
//                    if (error != null && error.contains("deleted"))       throw new TwitterException.SuspendedUser(error + "\n" + url);
//                    throw new TwitterException.E404(error + "\n" + url);
//                }
//                if (code == 406)     throw new TwitterException.E406(error + "\n" + url);
//                if (code == 413)     throw new TwitterException.E413(error + "\n" + url);
//                if (code == 416)     throw new TwitterException.E416(error + "\n" + url);
//                if (code == 420)     throw new TwitterException.TooManyLogins(error + "\n" + url);
//                if (code >= 500 && code < 600)     throw new TwitterException.E50X(error + "\n" + url);
//                processError2_rateLimit(connection,code,error);
//                throw new TwitterException(code + " " + error+ " "+ url);
//            }
//            catch (  SocketTimeoutException e) {
//                URL url=connection.getURL();
//                throw new TwitterException.Timeout(timeout + "milli-secs for " + url);
//            }
//            catch (  ConnectException e) {
//                URL url=connection.getURL();
//                throw new TwitterException.Timeout(url.toString());
//            }
//            catch (  SocketException e) {
//                throw new TwitterException.E50X(e.toString());
//            }
//            catch (  IOException e) {
//                throw new TwitterException(e);
//            }
//        }
//      /*  try(Scanner scanner = new Scanner(socket.getInputStream());
//            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true)) {
//            if(scanner.hasNextLine()){
//                printWriter.println("you write"+scanner.hasNextLine());
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }*/
//    }
//}
