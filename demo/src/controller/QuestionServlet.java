package controller;

import domains.QuestionUser;
import domains.User;
import services.QuestionService;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.*;

/**
 * Created by l on 3/19/2017.
 */
@WebServlet(name = "QuestionServlet")
public class QuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");

        checkSession(request, response, page);

        if (page.equalsIgnoreCase("questionForm")) {
            RequestDispatcher rd = request.getRequestDispatcher("question/questionForm.jsp");
            rd.forward(request, response);
        }
        if (page.equalsIgnoreCase("addQuestion")) {
            QuestionUser question = new QuestionUser();
            question.setQuestion(request.getParameter("question"));
            question.setOption1(request.getParameter("option1"));
            question.setOption2(request.getParameter("option2"));
            question.setOption3(request.getParameter("option3"));
            question.setOption4(request.getParameter("option4"));
            question.setCorrectanswer(request.getParameter("correctanswer"));
            question.setCategory(request.getParameter("category"));

            new QuestionService().insert(question);

            List<QuestionUser> questionList = new QuestionService().getQuestionList();
            request.setAttribute("questionList", questionList);
            RequestDispatcher rd = request.getRequestDispatcher("question/list.jsp");
            rd.forward(request, response);

        }
        if(page.equalsIgnoreCase("question")) {
            List<QuestionUser> questionList = new QuestionService().getQuestionList();
            request.setAttribute("questionList", questionList);
            RequestDispatcher rd = request.getRequestDispatcher("question/list.jsp");
            rd.forward(request, response);
        }
        if(page.equalsIgnoreCase("deleteList")){
            int id = parseInt(request.getParameter("id"));
            new QuestionService().deleteUser(id);

            List<QuestionUser> questionList = new QuestionService().getQuestionList();
            request.setAttribute("questionList", questionList);
            RequestDispatcher rd = request.getRequestDispatcher("question/list.jsp");
            rd.forward(request, response);
        }
        if(page.equalsIgnoreCase("editquestion")){
            QuestionUser question = new QuestionUser();
            question.setId(parseInt(request.getParameter("id")));
            question.setQuestion(request.getParameter("question"));
            question.setOption1(request.getParameter("option1"));
            question.setOption2(request.getParameter("option2"));
            question.setOption3(request.getParameter("option3"));
            question.setOption4(request.getParameter("option4"));
            question.setCorrectanswer(request.getParameter("correctanswer"));
            question.setCategory(request.getParameter("category"));

            new QuestionService().editUser(question);

            List<QuestionUser> questionList = new QuestionService().getQuestionList();
            request.setAttribute("questionList", questionList);
            RequestDispatcher rd = request.getRequestDispatcher("question/list.jsp");
            rd.forward(request, response);

        }
        if (page.equalsIgnoreCase("edit")) {
            int id = parseInt(request.getParameter("id"));
            QuestionUser question = new QuestionService().getUser(id);

            request.setAttribute("question", question);
            RequestDispatcher rd = request.getRequestDispatcher("question/edit.jsp");
            rd.forward(request, response);
        }

        if (page.equalsIgnoreCase("quiz")) {
            String nextQuestion = request.getParameter("next");
            QuestionUser question = null;
            if (nextQuestion == null) {
               question = new QuestionService().getQuestion("0");
                request.setAttribute("playIds","0");
                request.setAttribute("question", question);

            }else{
                int marks = 0;
                String option = request.getParameter("option");
                String correct_ans = request.getParameter("correct_ans");
                String id = request.getParameter("id");
                String playIds = request.getParameter("playIds");
                playIds = playIds + "," + id;

                if (correct_ans.equalsIgnoreCase(option)) {
                    marks = 5;
                    //save result
                }
               question = new QuestionService().getQuestion(playIds);
                request.setAttribute("question", question);
                request.setAttribute("playIds",playIds);
            }
            if (question == null) {
                //Display Result
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = request.getRequestDispatcher("question/quiz.jsp");
            rd.forward(request, response);
        }
    }

    private void checkSession(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        if (page == null) {
            page = "invalid";
        }

        if (!page.equalsIgnoreCase("login") && !page.equalsIgnoreCase("logout")) {
            HttpSession session = request.getSession(false);
            User user = (User)session.getAttribute("user");
            if (user == null) {
                request.setAttribute("msg","Please login First!");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        }
       // return page;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
