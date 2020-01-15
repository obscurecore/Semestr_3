window.onload = function () {
    document.getElementById("button").onclick = function () {

        let div = document.getElementById("div2");
        let i = 0;
        let client_height = screen.height;
        let timer = setInterval(function () {

            if (i >= client_height) {

                clearInterval(timer);
            } else {
                div.style.top = i.toString() + "px";
                i++;
            }
        }, 1);
    }
};