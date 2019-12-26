<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 10.12.2019
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        .chatbox{
            display: none;
        }
        .message{
            background-color: lightgray;
            width: 500px;
            padding: 20px;
        }
        .message.msg {
            background-color: #ffffff;
            padding: 0 10px 10px 10px;
            overflow: hidden;
        }
        .message.msg.from{
            background-color: cadetblue;
            line-height: 30px;
            text-align: center;
            color: white;
        }
        .message.msg.text{
            padding: 10px;

        }
        textarea.msg{
            width: 540px;
            padding: 10px;
            resize: none;
            border: none;
            box-shadow: 2px 2px 5px 0 inset;
        }

    </style>
    <script>

        let chatUnit={
            init(){
                this.startbox=document.querySelector(".start");
                this.chatbox= document.querySelector(".chatbox");
                this.startBtn = this.startbox.querySelector("button");
                this.nameInput= this.startbox.querySelector("input");


                this.msgTextArea = this.chatbox.querySelector("textarea");
                this.chatMessageContainer = this.chatbox.querySelector(".messages");



                this.bindEvents();
            },
            bindEvents(){
                this.startBtn.addEventListener("click",e=>this.openSocket())
                this.msgTextArea.addEventListener("keyup",ev => {
                    if(e.ctrlKey&&e.keyCode===13){
                        alert("TEST");
                        e.preventDefault();
                        this.send(this.msgTextArea.value);
                    }
                })
            },
            send(){
              this.sendMessage({
                  name:this.name,
                  text:this.msgTextArea.value
              });
            },
            onOpenSock (){
            },
            onMessage (parse){
                let msgBlock = document.createElement("div");
                msgBlock.className="msg";
                let fromBlock = document.createElement("div");
                fromBlock.className = "from";
                fromBlock.innerText=msg.name;

                let textBlok = document.createElement("div");
                textBlok.className = "text";
                textBlok.innerText=msg.text;


                msgBlock.appendChild(fromBlock);
                msgBlock.appendChild(textBlok);
                this.chatMessageContainer.appendChild(msgBlock);
            },
            onClose (){
            },
            sendMessage(msg){
                this.ws.send(JSON.stringify(msg))
            },
            openSocket(){
                this.ws = new WebSocket("ws://localhost:8100/sock/chat");
                this.ws.onopen=()=>this.onOpenSock()
                this.ws.onmessage=(e)=>this.onMessage(JSON.parse(e.data));
                this.ws.onclose=(ev => this.onClose())


                this.name=this.nameInput.value;
                this.startbox.style.display="none";
                this.chatbox.style.display="block";
            }
        };
        window.addEventListener("load", e=>chatUnit.init());

    </script>
</head>
<body>
<h1>ChatBox</h1>
<div class = "start">
    <input type="text" class ="username" placeholder="enter name...">
    <button id ="start" >start</button>
</div>
<div class ="chatbox">
    <div class ="messages">

    </div>
    <textarea class="msg">

    </textarea>
</div>
</body>
</html>
