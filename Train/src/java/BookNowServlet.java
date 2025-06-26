import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookNowServlet")
public class BookNowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String trainName = request.getParameter("train_name");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String departureTime = request.getParameter("departure_time");

        // Input validation
        if (trainName == null || trainName.trim().isEmpty() ||
            source == null || source.trim().isEmpty() ||
            destination == null || destination.trim().isEmpty() ||
            departureTime == null || departureTime.trim().isEmpty()) {
            out.println("<script>alert('Error: All fields are required!'); window.history.back();</script>");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish database connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "");

            // Insert booking into the database
            String query = "INSERT INTO bookings (train_name, source, destination, departure_time) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, trainName);
            ps.setString(2, source);
            ps.setString(3, destination);
            ps.setString(4, departureTime);
            ps.executeUpdate();

            // Success response with background and styling
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Booking Successful</title>");
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
            out.println(".success-container {");
            out.println("    background: rgba(255, 255, 255, 0.95);");
            out.println("    padding: 2rem;");
            out.println("    border-radius: 12px;");
            out.println("    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);");
            out.println("    text-align: center;");
            out.println("}");
            out.println(".success-container h1 {");
            out.println("    color: #4CAF50;");
            out.println("    font-size: 1.5rem;");
            out.println("}");
            out.println(".success-container p {");
            out.println("    color: #333;");
            out.println("    font-size: 1rem;");
            out.println("}");
            out.println(".success-container a {");
            out.println("    color: #6c5ce7;");
            out.println("    text-decoration: none;");
            out.println("}");
            out.println(".success-container a:hover {");
            out.println("    text-decoration: underline;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"success-container\">");
            out.println("<h1>Booking Successful!</h1>");
            out.println("<p>Train: " + trainName + "</p>");
            out.println("<p>From: " + source + " To: " + destination + "</p>");
            out.println("<p>Departure Time: " + departureTime + "</p>");
            out.println("<a href=\"search.html\">Book Another Train</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            // Error response with background and styling
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Booking Error</title>");
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
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            out.println("<a href=\"search.html\">Try Again</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            // Close resources
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                out.println("<h3>Error closing resources: " + e.getMessage() + "</h3>");
            }
        }
    }
}