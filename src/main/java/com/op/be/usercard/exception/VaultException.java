package com.op.be.usercard.exception;

public class VaultException extends Exception{

	private static final long serialVersionUID = 4949507844667026946L;

	public VaultException(String errorMessage) {
        super(errorMessage);
    }

}