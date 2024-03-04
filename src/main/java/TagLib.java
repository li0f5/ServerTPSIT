import jakarta.servlet.jsp.*;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.sql.*;
import java.util.StringTokenizer;

public class TagLib extends TagSupport {

    /* SQL String used to query the database */
    private String sql;

    /* user name */
    private String user;

    /* password */
    private String password;

    /* DSN */
    private String dsn;

    /* fields list */
    private String fields;

    /* start the tag and perform the work */
    public int doStartTag()
            throws JspException
    {
        /* used to parse the fields list */
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
            connString = "jdbc:odbc:" + dsn;
            connString += ";UID=" + user + ";PWD=" + password;

            conn = DriverManager.getConnection(connString);
            /* build the statement and execute the query <b>(3)</b>*/
            Statement st = conn.createStatement();
            pageContext.getOut().println( conn.isValid(100) );


            ResultSet rs = st.executeQuery( sql );

            /* loop over the data <b>(4)</b>*/
            pageContext.getOut().println( "<table>" );
            while( rs.next() )
            {
                /* display fields */
                pageContext.getOut().println( "<tr>" );

                /* parse the fields list <b>(5)</b>*/
                t = new StringTokenizer( fields, "," );

                while( t.hasMoreTokens() )
                {
                    /* display a single field <b>(6)</b> */
                    pageContext.getOut().println( "<td>" );
                    pageContext.getOut().println( rs.getString( t.nextToken()));
                    pageContext.getOut().println( "</td>" );
                }

                /* close the line <b>(7)</b>*/
                pageContext.getOut().println( "</tr>" );
            }
            pageContext.getOut().println( "</table>" );

            /* close the recordset <b>(8)</b>*/
            rs.close();
            st.close();
            conn.close();
        }
        catch( Exception e )
        {
            /* any exception */
            throw new JspException( "query:" + e.getMessage() );
        }

        return SKIP_BODY;
    }

    /* close the tag */
    public int doEndTag()
    {
        return EVAL_PAGE;
    }

    /* set/get the fields list */
    public void setfields( String fields )
    {
        this.fields = fields;
    }
    public String getfields()
    {
        return fields;
    }

    /* set/get the SQL string */
    public void setsql( String sql )
    {
        this.sql = sql;
    }
    public String getsql()
    {
        return sql;
    }

    /* set/get the username */
    public void setuser( String user )
    {
        this.user = user;
    }
    public String getuser()
    {
        return user;
    }

    /* set/get the password */
    public void setpassword( String password )
    {
        this.password = password;
    }
    public String getpassword()
    {
        return password;
    }

    /* set/get the dsn */
    public void setdsn( String dsn )
    {
        this.dsn = dsn;
    }
    public String getdsn()
    {
        return dsn;
    }
}
