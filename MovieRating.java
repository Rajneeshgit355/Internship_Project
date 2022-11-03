//	User Movie Rating API.....................................................................................
		@GetMapping("/UserRating/Imdb")
		public String Userrating(HttpServletRequest req) throws ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "Alter355@");
			String userid = req.getParameter("userid");
			String moviename = req.getParameter("moviename");
			String movierating = req.getParameter("movierating");
			String query1 = "select * from imdb.imdb_user where Mail = '"+userid+"'";
			PreparedStatement psmt1 = con.prepareStatement(query1);
			double avr = Double.parseDouble(movierating);
			ResultSet rs = psmt1.executeQuery();
			if(rs.next())
			{
				String query = "insert into imdbrating (Mail, Movie_Name,Rating,ratingday) values (?,?,?,Now())";
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setString(1,userid);
				psmt.setString(2,moviename);
				psmt.setString(3,movierating);
				int i = psmt.executeUpdate();
				String query3 = "select count(Movie_Name) as ra from imdbrating where Movie_Name= '"+moviename+"'";
				PreparedStatement psmt3 = con.prepareStatement(query3);
				ResultSet rs3 = psmt3.executeQuery();
				int n=0;
				while (rs3.next())
				{
				 n = rs3.getInt("ra");
				}
					String query4 = "select Rating as rate from movie_detail where Movie_Name= '"+moviename+"'";
				PreparedStatement psmt4 = con.prepareStatement(query4);
				ResultSet rs4 = psmt4.executeQuery();
				float oldrating=0;
				while (rs4.next())
				{
					 oldrating = rs4.getFloat("rate");
				}
				float newrating = (float) ((oldrating+avr)/n);
				String query5 = "update movie_detail set Rating='"+newrating+"'where Movie_Name='"+moviename+"'";
				PreparedStatement psmt5 = con.prepareStatement(query5);
				psmt5.executeUpdate();
				if(i>0)
					return "Sucessfully Rated";
				else
					return "Something went wrong";
			}
			else 
			{
				return "You are not valid user";
			}
			
			}
		