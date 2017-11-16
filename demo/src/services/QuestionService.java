package services;

import domains.QuestionUser;
import domains.User;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 3/19/2017.
 */
public class QuestionService {

    public List<QuestionUser> getQuestionList() {
        List<QuestionUser> questionList = new ArrayList<QuestionUser>();
        String query = "select * from question";
        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        try {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                QuestionUser question = new QuestionUser();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setOption1(rs.getString("option1"));
                question.setOption2(rs.getString("option2"));
                question.setOption3(rs.getString("option3"));
                question.setOption4(rs.getString("option4"));
                question.setCorrectanswer(rs.getString("correctanswer"));
                question.setCategory(rs.getString("category"));
                questionList.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }


    public void insert(QuestionUser question) {
        String query = "insert into question(question,option1,option2,option3,option4, correctanswer,category) values(?,?,?,?,?,?,?)";

        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        try {
            pstm.setString(1, question.getQuestion());
            pstm.setString(2, question.getOption1());
            pstm.setString(3, question.getOption2());
            pstm.setString(4, question.getOption3());
            pstm.setString(5, question.getOption4());
            pstm.setString(6, question.getCorrectanswer());
            pstm.setString(7, question.getCategory());

            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String query = "delete from question where id =?";

        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        try {
            pstm.setInt(1, id);
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editUser(QuestionUser question){
        String query = "update question set question=?,option1=?,option2=?,option3=?,option4=?,category=?,correctanswer=? where id=?";

        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        try {
            pstm.setString(1, question.getQuestion());
            pstm.setString(2, question.getOption1());
            pstm.setString(3, question.getOption2());
            pstm.setString(4, question.getOption3());
            pstm.setString(5, question.getOption4());
            pstm.setString(6, question.getCategory());
            pstm.setString(7, question.getCorrectanswer());
            pstm.setInt(8,question.getId());
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public QuestionUser getUser(int id){
        String query="select * from question where id=?";
        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        QuestionUser question = null;
        try{
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                question = new QuestionUser();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setOption1(rs.getString("option1"));
                 question.setOption2(rs.getString("option2"));
                question.setOption3(rs.getString("option3"));
                question.setOption4(rs.getString("option4"));
                question.setCorrectanswer(rs.getString("correctanswer"));
                question.setCategory(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }


    public QuestionUser getQuestion(String ids){

        String[] playIds = ids.split(",");
        String values = "";

        String query="select * from question where id not in(";
        for (String playId : playIds) {
            values += playId+",";
        }

        query = query + values;
        int ind = query.lastIndexOf(",");
        query = query.substring(0, ind);
        query = query + ") limit 1";

        System.out.println(query);

        PreparedStatement pstm = new DatabaseConnection().getPreparedStatement(query);
        QuestionUser question = null;
        try{
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                question = new QuestionUser();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setOption1(rs.getString("option1"));
                question.setOption2(rs.getString("option2"));
                question.setOption3(rs.getString("option3"));
                question.setOption4(rs.getString("option4"));
                question.setCorrectanswer(rs.getString("correctanswer"));
                question.setCategory(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }
}

