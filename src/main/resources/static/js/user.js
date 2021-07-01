let index = {
    init: function () {
        $('#btn-save').on("click", () => { // function(){}이 아닌 화살표 함수를 사용하는 이유: this를 바인딩하기 위해서.
            this.save(); // function(){} 을 사용할 경우, 여기에서의 this는 window 객체를 가리킨다.
        });
    },
    save: function () {
        let data = {
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val()
        }

        // ajax 호출시 default가 비동기 호출
        $.ajax({
            type: 'POST',
            url: '/blog/api/user',
            data: JSON.stringify(data), // http body 데이터. 자바스크립트 오브젝트를 JSON으로 변환.
            contentType: 'application/json; charset=utf-8', // body 데이터가 어떤 타입인지(MIME)
            dataType: 'json' // 서버로 요청해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면)  => 자바스크립트 오브젝트로 변경해줌
        }).done(function (resp) {
            alert("회원가입이 완료되었습니다.");
            console.log(resp);
            location.href = "/blog";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        }); // ajax 통신을 이용해서 3개의 데이터를 JSON으로 변경하여 insert 요청

        /* 회원가입에 ajax를 사용하는 이유
            1. 요청에 대한 응답을 HTML이 아닌 Data(JSON)로 받기 위해서.
                - 서버는 화면을 요청할 시 클라이언트의 요청에 대해 HTML 파일로 응답한다.
                - 화면이 아니라 작업을 요청할 수도 있는데, 회원가입을 요청하면 서버는 회원가입을 수행하기 위해 DB에 연결하고, 다시 HTML 파일을 리턴한다.
                - 그러나 클라이언트가 웹 브라우저가 아닐 가능성이 존재한다. 클라이언트가 앱일 경우에는 HTML 파일을 이해하지 못한다.
                - 그렇기에 작업을 요청할 때는 클라이언트의 종류에 상관없이 Data를 리턴하도록 통일하는 것이 좋다.
            2. 비동기 통신을 하기 위해서.
                - 작업을 순차적으로 수행할 경우, 다른 작업은 이전 작업이 완료될 때까지 기다려야 하는 비효율이 발생한다.
                - ajax를 사용하면 회원가입을 수행하는 동안 다른 작업이 진행될 수 있도록 비동기 통신을 해줄 수 있음.
         */
    }
}

index.init();