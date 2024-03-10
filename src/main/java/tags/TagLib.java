package tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.sql.*;
import java.util.StringTokenizer;

public class TagLib extends SimpleTagSupport {

    String sql;
    String dsn;
    String user;
    String password;

    String fields;

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getdsn() {
        return dsn;
    }

    public void setdsn(String dsn) {
        this.dsn = dsn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        StringTokenizer t;
        String connString;
        int fieldnum;

        /* try to connect to the database */
        try
        {

            /* load the database driver and open the connection <b>(1)</b>*/
            Connection conn;
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            /* build the connection string <b>(2)</b>*/
            connString = "jdbc:mariadb://" + dsn;
            connString += ";UID=" + user + ";PWD=" + password;

            conn = DriverManager.getConnection(connString);

            /* build the statement and execute the query <b>(3)</b>*/
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( sql );

            /* loop over the data <b>(4)</b>*/
            out.println( "<table>" );
            while( rs.next() )
            {
                /* display fields */
                out.println( "<tr>" );

                /* parse the fields list <b>(5)</b>*/
                t = new StringTokenizer( fields, "," );

                while( t.hasMoreTokens() )
                {
                    /* display a single field <b>(6)</b> */
                    out.println( "<td>" );
                    out.println( rs.getString( t.nextToken()));
                    out.println( "</td>" );
                }

                /* close the line <b>(7)</b>*/
                out.println( "</tr>" );
            }
            out.println( "</table>" );

            /* close the recordset <b>(8)</b>*/
            rs.close();
            st.close();
            conn.close();
        }
        catch( Exception e )
        {
            out.println("connessione non riuscita");
            /* any exception */
            throw new JspException( "query:" + e.getMessage() );
        }
    }
}