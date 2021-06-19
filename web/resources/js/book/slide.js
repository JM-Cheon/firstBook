;(function($){
    var eventWrap = {
        init:function(){

            this.section1Fn();



        },
        section1Fn:function(){
            var $slideWrap      = $('.main .banner .slide-wrap');
            var $nextBtn        = $('.main .banner .next-btn');
            var $prevBtn        = $('.main .banner .prev-btn');
            var $slideW         = $('.main .banner .slide').innerWidth();
            var cnt             = 0;

            function mainSlideFn(){
                $slideWrap.stop().animate({left:cnt*-$slideW},600,function(){
                    if(cnt>2){cnt=0}
                    if(cnt<0){cnt=2}
                    $slideWrap.stop().animate({left:cnt*-$slideW},0);
                })
            }

            function nextSlideFn(){
                if(!$slideWrap.is(':animated')){
                    cnt--;
                    mainSlideFn();
                }
            }
            function prevSlideFn(){
                if(!$slideWrap.is(':animated')){
                    cnt++;
                    mainSlideFn();
                }
            }

            function timerFn(){
                setInterval(prevSlideFn,4000);
            }timerFn();

            $nextBtn.on({
                click:function(){
                    nextSlideFn();
                }
            })
            $prevBtn.on({
                click:function(){
                    prevSlideFn();
                }
            })

        }
    }
    eventWrap.init();

})(jQuery);