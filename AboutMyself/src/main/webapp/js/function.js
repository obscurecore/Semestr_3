// Code By Webdevtrick ( https://webdevtrick.com )
var timelineSwiper = new Swiper ('.timeline .swiper-container', {
  direction: 'vertical',
  loop: false,
  speed: 1600,
  pagination: '.swiper-pagination',
  paginationBulletRender: function (swiper, index, className) {
    var year = document.querySelectorAll('.swiper-slide')[index].getAttribute('data-year');
    return '<span class="' + className + '">' + year + '</span>';
  },
  paginationClickable: true,
  nextButton: '.swiper-button-next',
  prevButton: '.swiper-button-prev',
  breakpoints: {
    768: {
      direction: 'horizontal',
    }
  }
});


$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

















/*Downloaded from https://www.codeseek.co/mayrinck/telegram-web-layout-desktop-like-NjmRqL */
$(document).ready(function(){
  /* make side menu show up */
  $(".trigger").click(function(){
    $(".overlay, .menuWrap").fadeIn(180);
    $(".menu").animate({opacity: '1', left: '0px'}, 180);
  });

  /* make config menu show up */
  $(".settings").click(function(){
    $(".config").animate({opacity: '1', right: '0px'}, 180);
    /* hide others */
    $(".menuWrap").fadeOut(180);
    $(".menu").animate({opacity: '0', left: '-320px'}, 180);
  });

  // Show/Hide the other notification options
  $(".deskNotif").click(function(){
    $(".showSName, .showPreview, .playSounds").toggle();
  });

  /* close all overlay elements */
  $(".overlay").click(function () {
    $(".overlay, .menuWrap").fadeOut(180);
    $(".menu").animate({opacity: '0', left: '-320px'}, 180);
    $(".config").animate({opacity: '0', right: '-200vw'}, 180);
  });

  /* small conversation menu */
  $(".otherOptions").click(function(){
    $(".moreMenu").slideToggle("fast");
  });

  /* clicking the search button from the conversation focus the search bar outside it, as on desktop */
  $( ".search" ).click(function() {
    $( ".searchChats" ).focus();
  });

  /* Show or Hide Emoji Panel */
  $(".emoji").click(function(){
    $(".emojiBar").fadeToggle(120);
  });

  /* if the user click the conversation or the type panel will also hide the emoji panel */
  $(".convHistory, .replyMessage").click(function(){
    $(".emojiBar").fadeOut(120);
  });
});