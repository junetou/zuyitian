function getRootPath(){  
    return "";
}

var url = getRootPath() + "/portal/district/province";
 
$.getJSON(url, {"id":1}, function(resp) {

    var provId = $("#distProv").data("s");
    var cityId = $("#distCity").data("s");
    var areaId = $("#distArea").data("s");
    
    var htmls = "";
    for (var i = 0; i < resp.length; i++) {
        if (provId == resp[i].id) {
            htmls = htmls + "<option value='"+resp[i].id+"' selected='selected'>"+resp[i].name+"</option>";
        } else {
            htmls = htmls + "<option value='"+resp[i].id+"'>"+resp[i].name+"</option>";
        }
        if (i == 0) {
            if (provId == null || provId == "") {
                provId = resp[i].id;
            }
        }
    }
    $(htmls).appendTo("#distProv");

    var urlCity = getRootPath() + "/portal/district/city";
    $.getJSON(urlCity, {"id":provId}, function(resp) {
        var htmls = "";
        for (var i = 0; i < resp.length; i++) { 
            if (cityId == resp[i].id) {
                htmls = htmls + "<option value='"+resp[i].id+"' selected='selected'>"+resp[i].name+"</option>";
            } else {
                htmls = htmls + "<option value='"+resp[i].id+"'>"+resp[i].name+"</option>";
            }
            if (i == 0) {
                if (cityId == null || cityId == "") {
                    cityId = resp[i].id;
                }
            }
        }
        if (cityId != null && cityId != "") {
            var urlArea = getRootPath() + "/portal/district/area";
            $.getJSON(urlArea, {"id":cityId}, function(resp) {
                var htmls = "";
                for (var i = 0; i < resp.length; i++) {
                    if (areaId == resp[i].id) {
                        htmls = htmls + "<option value='"+resp[i].id+"' selected='selected'>"+resp[i].name+"</option>";
                    } else {
                        htmls = htmls + "<option value='"+resp[i].id+"'>"+resp[i].name+"</option>";
                    }
                }
                $(htmls).appendTo("#distArea");
            });

        }
        $(htmls).appendTo("#distCity");
    });
    
    
    
})

$(document).ready(function(){  
    var urlCity = getRootPath() + "/portal/district/city";
    $('#distProv').change(function(){  
        var id = $(this).val();
        $.getJSON(urlCity, {"id":id}, function(resp) {
            var htmls = "";
            var cityId = 1;
            for (var i = 0; i < resp.length; i++) {
                if (i == 0) {
                    cityId = resp[i].id;
                }
                htmls = htmls + "<option value='"+resp[i].id+"'>"+resp[i].name+"</option>";
            }
            $("#distCity").html(htmls);

            var urlArea = getRootPath() + "/portal/district/area";
            $.getJSON(urlArea, {"id":cityId}, function(resp) {
                var htmls = "";
                for (var i = 0; i < resp.length; i++) {
                    htmls = htmls + "<option value='"+resp[i].id+"'>"+resp[i].name+"</option>";
                }
                $("#distArea").html(htmls);
            });
        });
    });
    var urlArea = getRootPath() + "/portal/district/area";
    $('#distCity').change(function(){  
        var id = $(this).val();
        $.getJSON(urlArea, {"id":id}, function(resp) {
            var htmls = "";
            for (var i = 0; i < resp.length; i++) {
                htmls = htmls + "<option value='"+resp[i].id+"'>"+resp[i].name+"</option>";
            }
            $("#distArea").html(htmls);
        });
    });
});