<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${board.id}" />
        <div class="form-group">
            <input type="text" class="form-control" placeholder="제목을 입력하세요." id="title" value="${board.title}">
        </div>
        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">수정 완료</button>
</div>
<br/>
<script>
    $('.summernote').summernote({
        placeholder: '내용을 입력하세요.',
        tabsize: 2,
        height: 300
    });
</script>
<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
