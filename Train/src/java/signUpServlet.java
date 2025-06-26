import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class signUpServlet extends HttpServlet {

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
            String username = "root";
            String password = "";

            // Establish connection
            Connection con = DriverManager.getConnection(url, username, password);

            // Get user input
            String un1 = request.getParameter("Username");
            String pw1 = request.getParameter("Password");
            String email = request.getParameter("email");

            // Insert user into the database
            String sql = "INSERT INTO login (username, password, email) VALUES ('" + un1 + "', '" + pw1 + "', '" + email + "')";
            Statement st = con.createStatement();
            int n = st.executeUpdate(sql);

            // Add the same background image and styling as the HTML files
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Sign Up Success</title>");
            out.println("<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap' rel='stylesheet'>");
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
            out.println(".success-container {");
            out.println("    background: rgba(255, 255, 255, 0.95);");
            out.println("    padding: 2rem;");
            out.println("    border-radius: 12px;");
            out.println("    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);");
            out.println("    text-align: center;");
            out.println("}");
            out.println(".success-container h1 {");
            out.println("    color: #4CAF50;");
            out.println("    font-size: 1.75rem;");
            out.println("}");
            out.println(".success-container p {");
            out.println("    color: #333;");
            out.println("    font-size: 1rem;");
            out.println("}");
            out.println(".success-container a {");
            out.println("    display: inline-block;");
            out.println("    margin-top: 1rem;");
            out.println("    padding: 0.75rem 1.5rem;");
            out.println("    background-color: #6c5ce7;");
            out.println("    color: white;");
            out.println("    text-decoration: none;");
            out.println("    border-radius: 6px;");
            out.println("    transition: background-color 0.3s ease;");
            out.println("}");
            out.println(".success-container a:hover {");
            out.println("    background-color: #4a3dc2;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Success message with background and styling
            out.println("<div class='success-container'>");
            out.println("<h1>Sign Up Successful!</h1>");
            out.println("<p>Your account has been created successfully...!</p>");
            out.println("<a href='index.html'>Login</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            // Close resources
            st.close();
            con.close();
        } catch (Exception e) {
            // Error response with background and styling
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Sign Up Error</title>");
            out.println("<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap' rel='stylesheet'>");
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
            out.println("    font-size: 1.75rem;");
            out.println("}");
            out.println(".error-container a {");
            out.println("    display: inline-block;");
            out.println("    margin-top: 1rem;");
            out.println("    padding: 0.75rem 1.5rem;");
            out.println("    background-color: #6c5ce7;");
            out.println("    color: white;");
            out.println("    text-decoration: none;");
            out.println("    border-radius: 6px;");
            out.println("    transition: background-color 0.3s ease;");
            out.println("}");
            out.println(".error-container a:hover {");
            out.println("    background-color: #4a3dc2;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='error-container'>");
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            out.println("<a href='signup.html'>Try Again</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}