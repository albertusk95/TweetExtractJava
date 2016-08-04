package ak.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/*
    public SigninServlet() {
        super();
        System.out.println("ctor SigninServlet");
        // TODO Auto-generated constructor stub
    }
	*/
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			
			Twitter twitter = new TwitterFactory().getInstance();
			RequestToken requestToken;
			request.getSession().setAttribute("twitter", twitter);
			twitter.setOAuthConsumer("Consumer key", "Secret key");			
			
			// callback url
			requestToken = twitter.getOAuthRequestToken("http://127.0.0.1:8080/MyServletEclipse/views/redirect.jsp");			
			String authURL = requestToken.getAuthenticationURL();
			
			response.getWriter().append("authURL: " + authURL);
			
			request.getSession().setAttribute("requestToken", requestToken);
			response.sendRedirect(authURL);
		} catch (TwitterException  twitterException) {
			twitterException.printStackTrace();
		}
	}

}
