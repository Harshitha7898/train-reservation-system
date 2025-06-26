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

@WebServlet("/SearchTrainServlet")
public class SearchTrainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "");

            String query = "SELECT * FROM trains WHERE source = ? AND destination = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, source);
            ps.setString(2, destination);

            ResultSet rs = ps.executeQuery();

            // Add the same background image and styling as the HTML files
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Available Trains</title>");
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
            out.println(".table {");
            out.println("    margin-top: 1rem;");
            out.println("}");
            out.println(".btn-success {");
            out.println("    background-color: #6c5ce7;");
            out.println("    border: none;");
            out.println("    border-radius: 6px;");
            out.println("    padding: 0.5rem 1rem;");
            out.println("    font-size: 0.9rem;");
            out.println("    transition: background-color 0.3s ease;");
            out.println("}");
            out.println(".btn-success:hover {");
            out.println("    background-color: #4a3dc2;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Container for available trains
            out.println("<div class='container'>");
            out.println("<h2 class='text-center mb-4'>Available Trains</h2>");
            out.println("<table class='table table-bordered'>");
            out.println("<tr><th>Train Name</th><th>Source</th><th>Destination</th><th>Departure Time</th><th>Action</th></tr>");

            boolean found = false;
            while (rs.next()) {
                found = true;
                out.println("<tr>");
                out.println("<td>" + rs.getString("train_name") + "</td>");
                out.println("<td>" + rs.getString("source") + "</td>");
                out.println("<td>" + rs.getString("destination") + "</td>");
                out.println("<td>" + rs.getString("departure_time") + "</td>");
                out.println("<td>");
                out.println("<form action='BookNowServlet' method='post'>");
                out.println("<input type='hidden' name='train_name' value='" + rs.getString("train_name") + "'>");
                out.println("<input type='hidden' name='source' value='" + rs.getString("source") + "'>");
                out.println("<input type='hidden' name='destination' value='" + rs.getString("destination") + "'>");
                out.println("<input type='hidden' name='departure_time' value='" + rs.getString("departure_time") + "'>");
                out.println("<button type='submit' class='btn btn-success'>Book Now</button>");
                out.println("</form>");
                out.println("</td></tr>");
            }

            if (!found) {
                out.println("<tr><td colspan='5' class='text-center'>No trains found</td></tr>");
            }

            out.println("</table>");
            out.println("<div class='text-center mt-4'><a href='search.html' class='btn btn-primary'>Back to Search</a></div>");
            out.println("</div></body></html>");

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            out.println("<h3 class='text-danger text-center'>Error: " + e.getMessage() + "</h3>");
        }
    }
}