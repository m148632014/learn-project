package org.mfm.learn.springhateoas.exception;
/**
 * 请求资源时拒绝访问
 * 
 * @author MFM
 *
 */
public class AccessDeniedException extends RuntimeException{
	private static final long serialVersionUID = 1655593950053738714L;

	public AccessDeniedException() {
		 super("Access denied when requesting the resource.");
	}
}
