
var webSlider = new Slider($('.web-news'), {
        time: 5000,
        delay: 400,
        event: 'hover',
        auto: false,
        mode: 'fade',
        controller: $('#web-ctrl'),
        activeControllerCls: 'active'
    });

var webSlider = new Slider($('.forum-news'), {
        time: 5000,
        delay: 400,
        event: 'hover',
        auto: false,
        mode: 'fade',
        controller: $('#forum-ctrl'),
        activeControllerCls: 'active'
    });

$('.people-list .tab li, .people-list .imgs li').hover(function(){
	var _index = $(this).index();
	$(this).parents('.people-list').find('.tab li:eq('+_index+')').addClass('active').siblings().removeClass('active');
	$(this).parents('.people-list').find('.imgs').hide();
	$(this).parent().siblings('.tab-content').find('li:eq('+_index+')').addClass('active').siblings().removeClass('active');
});