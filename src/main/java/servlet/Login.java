package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // ユーザーリストをセッションから取得
        HttpSession session = request.getSession();
        List<User> userList = (List<User>) session.getAttribute("userList");

        boolean isLogin = false;
        User loginUser = null;

        // ユーザーリストから一致するユーザーを検索
        if (userList != null) {
            for (User user : userList) {
                if (user.getName().equals(name) && user.getPass().equals(pass)) {
                    isLogin = true;
                    loginUser = user;
                    break;
                }
            }
        }

        // ログイン成功時の処理
        if (isLogin) {
            session.setAttribute("loginUser", loginUser);
        }

        // ログイン画面にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
        dispatcher.forward(request, response);
    }
}
