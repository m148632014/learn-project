package org.mfm.learn.springhateoas.exception;

/**
 * 请求资源不存在异常
 * 
 * @author admin
 *
 */
public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8367936183182780266L;

	public EntityNotFoundException() {
        super("Requested resource doesn't exist.");
    }
}
