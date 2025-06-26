import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");

            // Database connection details
            String url = "jdbc:mysql://localhost/ticket_reservation";
            String dbUsername = "root";
            String dbPassword = "";

            // Establish connection
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Get user input
            String un1 = request.getParameter("Username");
            String pw1 = request.getParameter("Password");

            // Query to check if the user exists
            String sql = "SELECT * FROM login WHERE username='" + un1 + "' AND password='" + pw1 + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                // Login successful - Store username in session
                HttpSession session = request.getSession();
                session.setAttribute("username", un1);
                response.sendRedirect("search.html"); // Redirect to search page
            } else {
                // Login failed - Show error message with background
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("<title>Login Failed</title>");
                out.println("<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap\" rel=\"stylesheet\">");
                out.println("<style>");
                out.println("body {");
                out.println("    font-family: 'Roboto', sans-serif;");
                out.println("    background: url('https://c4.wallpaperflare.com/wallpaper/9/859/737/train-canada-landscape-mountains-wallpaper-preview.jpg') no-repeat center center/cover;");
                out.println("    display: flex;");
                out.println("    justify-content: center;");
                out.println("    align-items: center;");
                out.println("    height: 100vh;");
                out.println("    margin: 0;");
                out.println("}");
                out.println(".error-container {");
                out.println("    background: rgba(255, 255, 255, 0.95);");
                out.println("    padding: 2rem;");
                out.println("    border-radius: 12px;");
                out.println("    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);");
                out.println("    text-align: center;");
                out.println("}");
                out.println(".error-container h1 {");
                out.println("    color: #ff6b6b;");
                out.println("    font-size: 1.5rem;");
                out.println("}");
                out.println(".error-container a {");
                out.println("    color: #6c5ce7;");
                out.println("    text-decoration: none;");
                out.println("}");
                out.println(".error-container a:hover {");
                out.println("    text-decoration: underline;");
                out.println("}");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class=\"error-container\">");
                out.println("<h1>Invalid username or password. Please try again.</h1>");
                out.println("<a href=\"index.html\">Go back to Login</a>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }

            // Close resources
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Error</title>");
            out.println("<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap\" rel=\"stylesheet\">");
            out.println("<style>");
            out.println("body {");
            out.println("    font-family: 'Roboto', sans-serif;");
            out.println("    background: url('https://c4.wallpaperflare.com/wallpaper/9/859/737/train-canada-landscape-mountains-wallpaper-preview.jpg') no-repeat center center/cover;");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    height: 100vh;");
            out.println("    margin: 0;");
            out.println("}");
            out.println(".error-container {");
            out.println("    background: rgba(255, 255, 255, 0.95);");
            out.println("    padding: 2rem;");
            out.println("    border-radius: 12px;");
            out.println("    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);");
            out.println("    text-align: center;");
            out.println("}");
            out.println(".error-container h1 {");
            out.println("    color: #ff6b6b;");
            out.println("    font-size: 1.5rem;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"error-container\">");
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            out.println("<a href=\"index.html\">Go back to Login</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}