//Insert Movie Api................................................................................................ 
		@PostMapping("/InsertMovie/Imdb")
		public String InsertMovie(HttpServletRequest req) throws ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "Alter355@");
			String moviename = req.getParameter("moviename");
			String movierating = req.getParameter("movierating");
			String releasedate = req.getParameter("releasedate");
			String Dirname = req.getParameter("Dirname");
			String Runtime = req.getParameter("Runtime");
			String type = req.getParameter("type");
			String budget = req.getParameter("budget");
			String query = "insert into movie_detail (Movie_Name,Rating,Release_Date, Director_Name,Runtime,Type,Budget) values (?,?,?,?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1,moviename);
			psmt.setString(2,movierating);
			psmt.setString(3,releasedate);
			psmt.setString(4,Dirname);
			psmt.setString(5,Runtime);
			psmt.setString(6,type);
			psmt.setString(7,budget);
			
			int i = psmt.executeUpdate();
			if(i>0)
				return "Data Inserted";
			else
				return "Something Went wrong";
	}