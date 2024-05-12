function getLocation(){
    window.navigator.geolocation.getCurrentPosition(function (position) {
        var latitude = position.coords.latitude;
        var longitude = position.coords.longitude;
        //alert(latitude);
        var location = longitude+","+latitude;
        //x.innerHTML = location;
        $("#loc").val(location);
    },function (msg) {
        alert("获取您的地理信息失败");
    });
}

setInterval(function () {
    getLocation();
    var loc = $("#loc").val()
    $.ajax({
        type:"get",
        url:"/loc/updateLoc/"+loc,
        success:function (res) {

        },
        error:function (res) {
            console.log("位置更新失败")
        }
    })
},10000);