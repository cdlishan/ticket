package com.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfirmTickets
 */
public class ConfirmTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int as[] = new int[1];
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmTickets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		String grade = request.getParameter("grade");
		MutipleThread m = new MutipleThread(new ThreadCallback(){

			@Override
			public void getCurrentSeatNumber(int n) {
				System.out.println("End Thread, the current number is: "+n);
				as[0] = n;
			}
			
		},Integer.parseInt(first),Integer.parseInt(second),Integer.parseInt(grade));
		Thread t = new Thread(m);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("a[0] is : "+as[0]);
		//request.setAttribute("current",as[0]); 
        //request.getRequestDispatcher("ticket.jsp").forward(request,response); 
		//int currentTicket = MutipleThread.getCurrentSeatByTrip(first, second);
		//System.out.println(currentTicket);
	}

}
