<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="templatemo_menu">
    <ul>
        <li><a href="home?page=home" class="current">Home</a></li>
        <c:if test="${sessionScope.user.role=='admin'}">
            <li><a href="userList?page=list">User</a></li>
            <li><a href="question?page=question">Question</a></li>
        </c:if>
        <li><a href="quiz?page=quiz">Play Quiz</a></li>
        <li><a href="logout?page=logout">Logout</a></li>
        <li> <h2>Welcome To: ${sessionScope.user.name}</h2></li>
    </ul>


</div> <!-- end of templatemo_menu -->
