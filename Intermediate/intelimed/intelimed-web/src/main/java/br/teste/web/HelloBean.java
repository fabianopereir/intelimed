package br.teste.web;

import javax.annotation.PostConstruct;

import maintest.HelloWorld;

public class HelloBean {

	private String hello;
	
	@PostConstruct
	public void init(){
		HelloWorld tmp = new HelloWorld();
		hello = tmp.getMessage();
	}

	/**
	 * @return the hello
	 */
	public String getHello() {
		return hello;
	}

	/**
	 * @param hello
	 *            the hello to set
	 */
	public void setHello(String hello) {
		this.hello = hello;
	}

}
