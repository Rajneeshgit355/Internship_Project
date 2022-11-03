//Search Movie Detail By Type....................................................................................
		@GetMapping("/type")
		public Map name(HttpServletRequest req) throws ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb","root","Alter355@");
			String name=req.getParameter("q");
			Statement stmt=con.createStatement();
			String query="SELECT * FROM imdb.movie_type where movie_type='"+name+"'";
			ResultSet rs=stmt.executeQuery(query);
			HashMap map=new HashMap();
			
			PreparedStatement pstmt=con.prepareStatement(query);
			ResultSet rs1=pstmt.executeQuery(query);
			while(rs.next())
			{
				map.put("Movie:"+rs.getString("movie_name"),"Type:"+rs.getString("movie_type"));
						
		   }
			
			return map;
			
		}