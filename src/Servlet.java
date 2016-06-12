import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/snakedb")
    private DataSource ds;

    public Servlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)

    throws ServletException,IOException

    {
        try {
        Connection con = ds.getConnection();
        Statement stmt = con.createStatement();
        String query = "select * from snakedb.snake_table";
        ResultSet rs = stmt.executeQuery(query);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
            String s = "<html>\n" +
                    "<head>\n" +
                    "    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
                    "    <title>Leaderboard</title>\n" +
                    "</head>\n" +
                    "<body>" +
                    "<a href=\"http:\\\\localhost:228\"><img src=\"mainmenu.jpg\" /></a> \n" +
                    "    <div id=\"article\"><center><h3>Leaderboard</h3></center>\n" +
                    "        <table width=\"100%\" border=\"1\" bordercolor=\"#7BB5FD\">\n" +
                    "            <tr>\n" +
                    "                <th width=\"4%\">ID</th>\n" +
                    "                <th width=\"48%\">Nickname</th>\n" +
                    "                <th width=\"48%\">Score</th>\n" +
                    "            </tr>\n";
            out.print(s);

        while (rs.next()) {
            out.print("<tr>");
            out.print("<td>" + rs.getInt("id") + "</td>");
            out.print("<td>" + rs.getString("nickname") + "</td>");
            out.print("<td>" + rs.getInt("score") + "</td>");
            out.print("</tr>");
            }
        out.print("</table></div></body></html>");
        } catch (SQLException e) {
        e.printStackTrace();
        }
        }


    public void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException{
    }
}