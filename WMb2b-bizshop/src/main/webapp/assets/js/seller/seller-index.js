/**
 * 菜单导航事件
 */
$(document).on('click', '.navtitle > li', function(){
	var _this = $(this), _class = _this.prop('class'), _parent = window.parent.document;
	if(_class == "account"){
		$(_parent).find('.navbar-nav li.account').click();
	}else if(_class == "post-product"){
		$(_parent).find('.navbar-nav li.post-product').click();
	}else if(_class == "brand"){
		$(_parent).find('.navbar-nav li.brand').click();
	}
});

