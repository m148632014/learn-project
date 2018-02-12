package org.mfm.learn.pattern.abstracts.decorator;

/**
 * 具体
 * 
 * @author MengFanmao
 *
 */
public class Decorator extends Component {

	// 维持一个对抽象构件对象的引用
	private Component component;

	// 注入一个抽象构件类型的对象
	public Decorator(Component component) {
		this.component = component;
	}

	@Override
	public void operation() {
		// 调用原有业务方法
		this.component.operation();
	}
}
