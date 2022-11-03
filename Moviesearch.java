//Movie Search API...........................................................................................	
		@GetMapping("/search")
		public Map searchmovie(HttpServletRequest req) throws ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb","root","Alter355@");
			String name=req.getParameter("q");
			Statement stmt=con.createStatement();
			String query="Select * from movie_detail where Movie_Name='"+name+"'";
			ResultSet rs=stmt.executeQuery(query);
			HashMap map=new HashMap();
			PreparedStatement pstmt=con.prepareStatement(query);
			ResultSet rs1=pstmt.executeQuery(query);
			while(rs.next())
			{
				map.put("Movie Id:"+rs.getInt("Movie_id"),"Movie Name: "+rs.getString("Movie_Name"));
				map.put("Rating:"+rs.getDouble("Rating"),"Release Date:"+rs.getDate("Release_Date"));
				map.put("Director Name:"+rs.getString("Director_Name"),"Runtime:"+rs.getTime("Runtime"));
				map.put("Movie-Type:"+rs.getString("Type"),"Budget:"+rs.getString("Budget"));
		   }
			
			return map;
		}	