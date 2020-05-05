package com.env.web.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class WebLogAcpect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 定义切入点
	 * @author lizheng
	 * @param 
	 * @return void 返回类型
	 * @throws
	 */
	@Pointcut("execution(public * com.env.web.controller..*.*(..))")
    public void webLog(){}
	
	/**
	 * 前置通知
	 * @author lizheng
	 * @param @param joinPoint
	 * @param @throws Throwable
	 * @return void 返回类型
	 * @throws
	 */
	@Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        
//        //获取目标方法的参数信息  
//        Object[] obj = joinPoint.getArgs();  
//        //AOP代理类的信息  
//        joinPoint.getThis();  
//        //代理的目标对象  
//        joinPoint.getTarget();  
//        //用的最多 通知的签名  
//        Signature signature = joinPoint.getSignature();  
//        //代理的是哪一个方法  
//        logger.info("代理的是哪一个方法"+signature.getName());  
//        //AOP代理类的名字  
//        logger.info("AOP代理类的名字"+signature.getDeclaringTypeName());  
//        //AOP代理类的类（class）信息  
//        signature.getDeclaringType();  
//        //获取RequestAttributes  
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();  
//        //从获取RequestAttributes中获取HttpServletRequest的信息  
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);  
//        //如果要获取Session信息的话，可以这样写：  
//        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);  
//        //获取请求参数
//        Enumeration<String> enumeration = request.getParameterNames();  
//        Map<String,String> parameterMap = Maps.newHashMap();  
//        while (enumeration.hasMoreElements()){  
//            String parameter = enumeration.nextElement();  
//            parameterMap.put(parameter,request.getParameter(parameter));  
//        }  
//        String str = JSON.toJSONString(parameterMap);  
//        if(obj.length > 0) {  
//            logger.info("请求的参数信息为："+str);
//        }  
        
//        1）、通过JoinPoint可以获得通知的签名信息，如目标方法名、目标方法参数信息等；
//        2）、通过RequestContextHolder来获取请求信息，Session信息；

	}
	
	/**
	 * 后置通知,在某连接点之后执行，通常在一个匹配的方法返回的时候执行.（可以在后置通知中绑定返回值）
	 * returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行
	 * 对于returning对应的通知方法参数为Object类型将匹配任何目标返回值 
	 * @author lizheng
	 * @param @param ret
	 * @param @throws Throwable
	 * @return void 返回类型
	 * @throws
	 */
	@AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
	}
	
	/** 
	 * 后置异常通知 
	 *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法； 
	 *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行， 
	 *           对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。 
	 * @param joinPoint 
	 * @param exception 
	 */  
//	@AfterThrowing(value = POINT_CUT,throwing = "exception")  
//	public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){  
//	    //目标方法名：  
//	    logger.info(joinPoint.getSignature().getName());  
//	    if(exception instanceof NullPointerException){  
//	        logger.info("发生了空指针异常!!!!!");  
//	    }  
//	}  
	
	/** 
	 * 后置最终通知（不论是正常返回还是异常退出） 
	 * @param joinPoint 
	 */  
//	@After(value = POINT_CUT)  
//	public void doAfterAdvice(JoinPoint joinPoint){ 
//	    logger.info("后置最终通知执行了!!!!");  
//	} 
	
	/** 
	 * 环绕通知： 
	 *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。 
	 *   环绕通知使用一个代理ProceedingJoinPoint类型的对象来管理目标对象，所以此通知的第一个参数必须是ProceedingJoinPoint类型。
	 *   在通知体内调用ProceedingJoinPoint的proceed()方法会导致后台的连接点方法执行。
	 *   proceed()方法也可能会被调用并且传入一个Object[]对象，该数组中的值将被作为方法执行时的入参。
	 */  
//	@Around(value = POINT_CUT)  
//	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){  
//	    logger.info("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());  
//	    try {  
//	        Object obj = proceedingJoinPoint.proceed();  
//	        return obj;  
//	    } catch (Throwable throwable) {  
//	        throwable.printStackTrace();  
//	    }  
//	    return null;  
//	}
	
//	定义切入点的时候需要一个包含名字和任意参数的签名，还有一个切入点表达式，如execution(public * com.example.aop...(..))
//	切入点表达式的格式：execution([可见性]返回类型[声明类型].方法名(参数)[异常])
//	其中[]内的是可选的，其它的还支持通配符的使用：
//	1) *：匹配所有字符
//	2) ..：一般用于匹配多个包，多个参数
//	3) +：表示类及其子类
//	4)运算符有：&&,||,!
//
//	切入点表达式关键词用例：
//	1）execution：用于匹配子表达式。
//	//匹配com.cjm.model包及其子包中所有类中的所有方法，返回类型任意，方法参数任意
//	@Pointcut(“execution(* com.cjm.model...(..))”)
//	public void before(){}
//
//	2）within：用于匹配连接点所在的Java类或者包。
//	//匹配Person类中的所有方法
//	@Pointcut(“within(com.cjm.model.Person)”)
//	public void before(){}
//	//匹配com.cjm包及其子包中所有类中的所有方法
//	@Pointcut(“within(com.cjm..*)”)
//	public void before(){}
//
//	3） this：用于向通知方法中传入代理对象的引用。
//	@Before(“before() && this(proxy)”)
//	public void beforeAdvide(JoinPoint point, Object proxy){
//	//处理逻辑
//	}
//
//	4）target：用于向通知方法中传入目标对象的引用。
//	@Before(“before() && target(target)
//	public void beforeAdvide(JoinPoint point, Object proxy){
//	//处理逻辑
//	}
//
//	5）args：用于将参数传入到通知方法中。
//	@Before(“before() && args(age,username)”)
//	public void beforeAdvide(JoinPoint point, int age, String username){
//	//处理逻辑
//	}
//
//	6）@within ：用于匹配在类一级使用了参数确定的注解的类，其所有方法都将被匹配。
//	@Pointcut(“@within(com.cjm.annotation.AdviceAnnotation)”)
//	－ 所有被@AdviceAnnotation标注的类都将匹配
//	public void before(){}
//
//	7）@target ：和@within的功能类似，但必须要指定注解接口的保留策略为RUNTIME。
//	@Pointcut(“@target(com.cjm.annotation.AdviceAnnotation)”)
//	public void before(){}
//
//	8）@args ：传入连接点的对象对应的Java类必须被@args指定的Annotation注解标注。
//	@Before(“@args(com.cjm.annotation.AdviceAnnotation)”)
//	public void beforeAdvide(JoinPoint point){
//	//处理逻辑
//	}
//
//	9）@annotation ：匹配连接点被它参数指定的Annotation注解的方法。也就是说，所有被指定注解标注的方法都将匹配。
//	@Pointcut(“@annotation(com.cjm.annotation.AdviceAnnotation)”)
//	public void before(){}
//	
//	10）bean：通过受管Bean的名字来限定连接点所在的Bean。该关键词是Spring2.5新增的。
//	@Pointcut(“bean(person)”)
//	public void before(){}




}
