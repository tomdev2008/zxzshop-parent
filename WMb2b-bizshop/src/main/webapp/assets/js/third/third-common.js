//三方配套服务企业公共js
WM.third = {
	//创建select element
	loadSelectElemOptions : function (data, elem, id) {
        if (!data) { return; }
        for (var i = 0, e; e = data[i++];) {
            if (id) {
                elem.append('<option value="' + e.id + '" data="' + id + '">' + e.name + '</option>');
            } else {
                elem.append('<option value="' + e.id + '">' + e.name + '</option>');
            }
        }
    },
    fnSelect : function (data, val, tag) {
        if (!data) { return; }
        for (var i = 0, e; e = data[i++];) {
            if (e.id == val) {
                return e[tag];
            }
        }
    },
    getOption : function (elem, bool) {
        var s, t;
        if (bool) {
            elem.children().each(function (i, e) {
                s = e.selected;
                if (s == true) {
                    t = $(e).html();
                    if (t == '请选择') {
                        t = '';
                    }
                    return;
                }
            });
        } else {
            elem.children().each(function (i, e) {
                s = e.selected;
                if (s == true) {
                    t = $(e).val();
                    return;
                }
            });
        }
        return t;
    },
    //判断是否选择地区
    isSelectAddr : function (p, c, t) {
        if (!p || !c || !t)
            return false;
        var haveProvince = false;
        var haveCity = false;
        var haveTown = false;
        p.children().each(function (i, e) {
            s = e.selected;
            if (s == true && i > 0) {
                haveProvince = true;
                return;
            }
        });
        if (haveProvince) {
            c.children().each(function (i, e) {
                s = e.selected;
                if (s == true && i > 0) {
                    haveCity = true;
                    return;
                }
            });
            if (haveCity)
            {
                var idx = 0;
                t.children().each(function (i, e) {
                    s = e.selected;
                    idx = i;
                    if (s == true && i > 0) {
                        haveTown = true;
                        return;
                    }
                });
                haveTown = idx > 0 ? haveTown : true;
            }
        }
        return haveProvince && haveCity && haveTown;
    },
    //初始化区域选择
	initRegion : function(_defaultP, _defaultC, _defaultA){
		try{
			var city = [];
	        var provinceDiv = $('#provinceDiv');
	        var cityDiv = $('#cityDiv');
	        var countyDiv = $('#countyDiv');
	        var areaName = $('#areaName');
	        WM.third.loadSelectElemOptions(province, provinceDiv);
            
	        $('#provinceDiv').change(function (e) {
	            var t = e.target,
	                id = $(this).val(),
	                city;
	            if (id != 0) {
	                city = WM.third.fnSelect(province, id, 'city');
	                cityDiv.html('<option value="0">请选择</option>');
	                countyDiv.html('<option value="0">请选择</option>');
	                WM.third.loadSelectElemOptions(city, cityDiv, id);
	                if(_defaultC){
	                	cityDiv.val(_defaultC);
	                }
	            }
	            return false;
	        });
            
	        $('#cityDiv').change(function (e) {
	            var t = e.target,
	                id = $(this).val(),
	                tag,
	                city,
	                county;
	            if (id != 0) {
	                tag = $(this).find("option:selected").attr('data');
	                city = WM.third.fnSelect(province, tag, 'city');
	                county = WM.third.fnSelect(city, id, 'county');
	                countyDiv.html('<option value="0">请选择</option>');
	                WM.third.loadSelectElemOptions(county, countyDiv);
	                if(_defaultA){
	                	countyDiv.val(_defaultA);
	                }
	            }
	        });

	        $('#countyDiv').change(function (e) {
	            var id = $(this).val();
	            if (id != 0) {
	                //areaName.find('span').eq(2).html($(this).find("option:selected").html());
	            }
	        }); 
			    
	        if(_defaultP){
	        	provinceDiv.val(_defaultP);
	        	$('#provinceDiv').change();
	        }	
	        if(_defaultC){
            	cityDiv.val(_defaultC);
                $('#cityDiv').change();
            }
	        if(_defaultA){
            	countyDiv.val(_defaultA);
            }
		}catch(exx){
			if(console){
				console.log(exx.stack);
			}
		}
        return 1;
	}
}