//MovieInfo Project Signup API......................................................................................
	@PostMapping("/MISignup")
	public String homesignup(HttpServletRequest req) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb","root","Alter355@");
		String name=req.getParameter("name");
		String mail=req.getParameter("mail");
		String password=req.getParameter("pwd");
		
		String checkexistence = "select * from imdb.imdb_user where mail = '"+mail+"'";
		PreparedStatement checkmail = con.prepareStatement(checkexistence);
		ResultSet rs = checkmail.executeQuery();
		if(rs.next()) {
			return mail+ " Alreaady exist Account";
		}
		
		else {
		String signupquery = "insert into imdb.imdb_user (name, mail, password) values(?,?,?)";
		PreparedStatement smt = con.prepareStatement(signupquery);
		smt.setString(1, name);
		smt.setString(2, mail);
		smt.setString(3, password);
		int i = smt.executeUpdate();
		if(i>0)
		return "Signup Successfully";	
		else 
		return "Something went wrong";
		    }
	}