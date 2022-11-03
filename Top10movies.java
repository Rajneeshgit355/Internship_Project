//Search Top 10 Ratd Movies.................................................................................
		@GetMapping("/top10")
		public Map collection(HttpServletRequest req) throws ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb","root","Alter355@");
//			String name=req.getParameter("q");
			Statement stmt=con.createStatement();
			String query="SELECT * FROM imdb.top10 limit 10";
			ResultSet rs=stmt.executeQuery(query);
			HashMap map=new HashMap();
			
			PreparedStatement pstmt=con.prepareStatement(query);
			ResultSet rs1=pstmt.executeQuery(query);
			while(rs.next())
			{
				map.put("Movie:"+rs.getString("movie_name"),"World-wide Collection:"+rs.getString("collection"));
						
		   }
			
			return map;
			
		}