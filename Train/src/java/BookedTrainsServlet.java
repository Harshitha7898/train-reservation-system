import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowBookingsServlet")
public class BookedTrainsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "");

            // Retrieve all bookings
            String query = "SELECT * FROM bookings";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // Add the same background image and styling as the HTML files
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>All Bookings</title>");
            out.println("<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap' rel='stylesheet'>");
            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
            out.println("<style>");
            out.println("body {");
            out.println("    font-family: 'Roboto', sans-serif;");
            out.println("    background: url('https://c4.wallpaperflare.com/wallpaper/9/859/737/train-canada-landscape-mountains-wallpaper-preview.jpg') no-repeat center center/cover;");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    min-height: 100vh;");
            out.println("    margin: 0;");
            out.println("}");
            out.println(".container {");
            out.println("    background: rgba(255, 255, 255, 0.95);");
            out.println("    padding: 2rem;");
            out.println("    border-radius: 12px;");
            out.println("    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);");
            out.println("}");
            out.println(".card {");
            out.println("    border: none;");
            out.println("    border-radius: 8px;");
            out.println("    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);");
            out.println("}");
            out.println(".card-title {");
            out.println("    color: #6c5ce7;");
            out.println("}");
            out.println(".btn-primary {");
            out.println("    background-color: #6c5ce7;");
            out.println("    border: none;");
            out.println("    border-radius: 6px;");
            out.println("    padding: 0.75rem 1.5rem;");
            out.println("    font-size: 1rem;");
            out.println("    transition: background-color 0.3s ease;");
            out.println("}");
            out.println(".btn-primary:hover {");
            out.println("    background-color: #4a3dc2;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Container for bookings
            out.println("<div class='container'>");
            out.println("<h2 class='text-center mb-4'>All Bookings</h2>");

            boolean found = false;
            while (rs.next()) {
                found = true;
                out.println("<div class='card mb-3'>");
                out.println("<div class='card-body'>");
                out.println("<h5 class='card-title'>" + rs.getString("train_name") + "</h5>");
                out.println("<p class='card-text'><strong>From:</strong> " + rs.getString("source") + "</p>");
                out.println("<p class='card-text'><strong>To:</strong> " + rs.getString("destination") + "</p>");
                out.println("<p class='card-text'><strong>Departure Time:</strong> " + rs.getString("departure_time") + "</p>");
                out.println("</div></div>");
            }

            if (!found) {
                out.println("<div class='alert alert-warning text-center'>No bookings available.</div>");
            }

            // Back to Search Button
            out.println("<div class='text-center mt-4'><a href='search.html' class='btn btn-primary'>Back to Search</a></div>");
            out.println("</div></body></html>");

            // Close connections
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            out.println("<h3 class='text-danger text-center'>Error: " + e.getMessage() + "</h3>");
        }
    }
}