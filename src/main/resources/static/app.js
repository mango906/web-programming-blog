const userId = 1;
let posts = [];
let user = [];

$(function () {
    init();
});

let init = async () => {
    await $.ajax({
        url: 'http://localhost:8080/getPost',
        type: 'GET',
        success: function (res) {
            posts = res.data;
            res.data.forEach((post) => {
                $(".board-nav").prepend(`<li class="post-${post.id}" onclick="getBoard(${post.id})">${post.title}</li>`);
            });
            console.log(res);
        }
    });

    await $.ajax({
        url: 'http://localhost:8080/getUser/minkb01',
        type: 'GET',
        success: function (res) {
            user = res.data;
            console.log(res);
            $(".user-id").text(`사용자 아이디: ${user.account}`);
            $(".user-name").text(`사용자 이름: ${user.name}`);
            $(".user-created").text(`가입일: ${user.created}`);
            $(".user-count").text(`게시물 수: ${findCnt(user.id)}`);
            console.log(user);
        }
    })
}

let postForm = () => {
    $(".section-title").html('<input style="width: 100%;" type="text" class="input-title" placeholder="제목">');
    $(".section-time").hide();
    $(".section-content").html('<textarea  cols="50" rows="10" class="input-content" placeholder="본문">');
    $(".section-content").append('<button class="postBtn" onclick="post()">작성</button>')
}

let post = () => {
    let title = $(".input-title").val();
    let content = $(".input-content").val();
    let req = {
        userId: 1,
        title: title.toString(),
        content: content.toString()
    };
    $.ajax({
        url: 'http://localhost:8080/addPost',
        type: "POST",
        data: JSON.stringify(req),
        contentType: "application/json",
        success: (res) => {
            console.log(res);
            if(res.code === 202){
                alert("글 등록 성공!");
                location.reload();
            }
        }
    });
}

let getBoard = (id) => {
    $.ajax({
        url: `http://localhost:8080/getPostById/${id}`,
        type: "GET",
        success: (res) => {
            $(".section-title").text(res.data.title);
            $(".section-writer").text(res.data.userId);
            $(".section-time").text(res.data.modified);
            $(".section-content").text(res.data.content);
            $(".tool-nav").empty();
            $(".tool-nav").append(`<button onclick="prev(${id})">이전</button>`);
            $(".tool-nav").append(`<button onclick="updatePostForm(${id})">수정</button>`);
            $(".tool-nav").append(`<button onclick="deletePost(${id})">삭제</button>`);
            $(".tool-nav").append(`<button onclick="next(${id})">다음<button>`);
        }
    })
};

let updatePostForm = (id) => {
    let post = posts.filter(post => post.id === id);
    console.log(post);
    $(".section-title").html(`<input type="text" class="input-title" placeholder="제목" value="${post[0].title}">`);
    $(".section-content").html(`<textarea cols="20" rows="10" class="input-content" placeholder="본문" value="${post[0].content}">`);
    $(".section-content").append(`<button onclick="update(${id})">수정</button>`);
    $(".tool-nav").hide();
}

let deletePost = (id) => {
    console.log(`http://localhost:8080/deletePost/${id}`);
    $.ajax({
        url: `http://localhost:8080/deletePost/${id}`,
        type: "DELETE",
        success: (res) => {
            if(res.code === 204){
                alert("글 삭제 성공!");
                location.reload();
            }
        }
    });
};

let update = (id) => {
    let title = $(".input-title").val();
    let content = $(".input-content").val();
    let req = {
        userId: 1,
        title: title.toString(),
        content: content.toString()
    };
    $.ajax({
        url: `http://localhost:8080/updatePost/${id}`,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(req),
        success: (res) => {
            if(res.code === 203){
                alert("글 수정 성공!");
                location.reload();
            }
            console.log(res);
        }
    });
};

let prev = (id) => {
    let idx = findIdx(id)-1;
    if(idx < 0){
        alert("더 불러올 게시물이 없어요!");
        return;
    }
    console.log(posts.length);
    console.log(idx);
    $(".section-title").text(posts[idx].title);
    $(".section-time").text(posts[idx].modified);
    $(".section-content").text(posts[idx].content);
}

let next = (id) => {
    let idx = findIdx(id)+1;
    if(idx > posts.length ){
        alert("더 불러올 게시물이 없어요!");
        return;
    }
    console.log(posts.length);
    console.log(idx);
    $(".section-title").text(posts[idx].title);
    $(".section-time").text(posts[idx].modified);
    $(".section-content").text(posts[idx].content);

};

let findIdx = (id) => {
    let idx = -1;
    posts.forEach((post, i) => {
        if(post.id === id)
            idx = i;
    });
    return idx;
};

let findCnt = (id) => {
    let idx = 0;
    posts.forEach((post, i) => {
        if(post.userId === id) idx++;
    });
    return idx;
};