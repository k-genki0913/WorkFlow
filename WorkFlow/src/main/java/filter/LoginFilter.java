package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Account;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
    //現状利用する予定はない
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	//WorkFlowアプリの全画面を利用する際に、ログインされているか確認し
	//ログインされていない場合はログイン画面へ移動させる
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//前処理でログイン確認を行う
		//ログインするとsessionスコープへ属性名「Account」が保存されるため、
		//sessionスコープからインスタンスを生成し、nullかインスタンスが実在するかでログインしているか判断する
		HttpSession session = ((HttpServletRequest)request).getSession();
		Account account = (Account)session.getAttribute("Account");
		
		if(account != null) {
			chain.doFilter(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	//現状利用する予定はない
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
