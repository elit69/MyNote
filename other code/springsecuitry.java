@Override
protected void configure(HttpSecurity http) throws Exception {

	
	http.anonymous();
	http.authorizeRequests();
	
	http.csrf().disable();
	
	http.authorizeRequests().antMatchers("/").permitAll();
	http.authorizeRequests().antMatchers("/*.ico").permitAll();
	http.authorizeRequests().antMatchers("/login.html").permitAll();
	
	
	http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
	http.authorizeRequests().antMatchers("/api/**").hasRole("USER");
	
	http.addFilterBefore(new WHTCustomFilter(), CsrfFilter.class);
	
	http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
		@Override
		public void handle(HttpServletRequest request,
				HttpServletResponse response,
				AccessDeniedException accessDeniedException) throws IOException,
				ServletException {
			
		}
	});
	
	
	http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
		@Override
		public void commence(HttpServletRequest request,
				HttpServletResponse response, AuthenticationException authException)
				throws IOException, ServletException {
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().println("Yo");
		}
	});
}