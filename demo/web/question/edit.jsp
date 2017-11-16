<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Demo Project</title>
    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css"/>
    <link href="css/table.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div id="templatemo_wrapper_outter">

    <div id="templatemo_wrapper_inner">

        <div id="templatemo_header">
            ${msg}
        </div>
        <!-- end of templatemo_header -->
        <%@include file="../includes/menu.jsp"%>        <!-- end of templatemo_menu -->

        <div id="templatemo_content_wrapper">

            <div id="templatemo_sidebar_wrapper">

                <div id="templatemo_sidebar_top"></div>
                <div id="templatemo_sidebar">

                    <h2>Our Services</h2>

                    <ul class="categories_list">
                        <li><a href="#">Test1</a></li>
                        <li><a href="#">Test2</a></li>
                    </ul>

                    <div class="cleaner_h30"></div>

                </div>
                <div id="templatemo_sidebar_bottom"></div>

            </div>


            <div id="templatemo_content">

                <div class="content_section">

                    <h2><a href="questionForm?page=questionForm">Add New Question</a></h2>

                    <form method="post" action="edit1">
                        <input type="hidden" name="page" value="editquestion">
                        <input type="hidden" name="id" value="${question.id}">
                        <table id="t01">
                             
                            <tr>
                                   
                                <th>question</th><td><input type="text" name="question"  value="${question.question}"></td>
                            </tr>
                            <tr>
                                   
                                <th>option1</th><td><input type="text" name="option1"   value="${question.option1}"></td>
                            </tr> 

                            <tr>
                                   
                                <th>option2</th><td><input type="text" name="option2"   value="${question.option2}"></td>
                            </tr> 

                            <tr>
                                   
                                <th>option3</th><td><input type="text" name="option3"   value="${question.option3}"></td>
                            </tr> 

                            <tr>
                                   
                                <th>option4</th><td><input type="text" name="option4"  value="${question.option4}"></td>
                            </tr> 

                            <tr>
                                   
                                <th>correctanswer</th><td><input type="text" name="correctanswer" value="${question.correctanswer}"></td>
                            </tr>  

                            <tr>
                                   
                                <th>category</th><td><input type="text" name="category"value="${question.category}"></td>
                            </tr> 
                            <tr>
                                   
                                <th>&nbsp;</th><td><input type="submit" value="Edit Question"></td>
                            </tr>
                        </table>
                    </form>
                </div>

            </div>

            <div class="cleaner"></div>

        </div> <!-- end of templatemo_content_wrapper -->

        <div id="templatemo_footer">

            This is Footer
        </div>
        <!-- end of footer -->

    </div> <!-- end of templatemo_wrapper_inner -->

</div> <!-- end of templatemo_wrapper_outter -->

</body>
</html>
