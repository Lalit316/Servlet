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

                    <form method="post" action="quiz">
                        <input type="hidden" name="page" value="quiz">
                        <input type="hidden" name="next" value="next">
                        <input type="hidden" name="id" value="${question.id}">
                        <input type="hidden" name="playIds" value="${playIds}">
                        <input type="hidden" name="correct_ans" value="${question.correctanswer}">


                        <h2>
                            ${question.question}
                        </h2>

                        <input type="radio" name="option"   value="${question.option1}">${question.option1}
                        <input type="radio" name="option"   value="${question.option2}">${question.option2}

                        <input type="radio" name="option"   value="${question.option3}">${question.option3}
                        <input type="radio" name="option"   value="${question.option4}">${question.option4}
                        <input type="submit" value="Next Question">

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
