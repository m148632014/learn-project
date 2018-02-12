package org.mfm.learn.springhateoas.exception;

/**
 * 用户不存在异常
 * @author MFM
 *
 */
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5691158368764248697L;

	public UserNotFoundException(String username) {
        super(String.format("User %s not found.", username));
    }
}
