Login API..............................................................................
	@PostMapping("/MIlogin")
	public Map login(HttpServletRequest req) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "Alter355@");
		String mail = req.getParameter("mail");
		String pass = req.getParameter("pwd");
		 Statement stmt =  con.createStatement();
		 String query = "select * from imdb.imdb_user where mail = '"+mail+"'";
		 ResultSet rs = stmt.executeQuery(query);
		 HashMap<String,String> map = new HashMap<String,String>();
		 if(rs.next()) {
			 if(rs.getString("password").equals(pass)){
				 map.put("message", "You are successfuly logged in");
				   return map;
			 }
			 else {
				 map.put("message", "You password is not  correct");
				   return map;
			 }
		 
		 }
		 else {
			 map.put("message", "You are not signed up please try to signup");
			 return map;
		 }
	
	}
	