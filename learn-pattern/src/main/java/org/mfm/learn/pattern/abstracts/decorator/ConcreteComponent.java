package org.mfm.learn.pattern.abstracts.decorator;

/**
 * 具体构件<br>
 * 比如某个service的实现类或者抽象类的继承者，均算是具体构件类
 *
 * @author MengFanmao
 *
 */
public class ConcreteComponent extends Component {
	@Override
	public void operation() {
		System.out.println("我是具体的构件类(被装饰对象)，这是我的原有方法");
	}

}
