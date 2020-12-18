package com.example.threadpool.designmode;

/**
 * @author Barret
 * @description
 * @date 11/21/2020
 */
public class FactoryMode {
    public static void main(String[] args) {
        Application application = new Application();
        Product product = application.getObj(1);
        product.method1();
    }
}
interface Product{
    public void method1();
}

//如果后面要增加ProductB，那么都会有一个相同方法method1
class ProductA implements Product{
    @Override
    public void method1(){
        System.out.println("productA method1");
    }
}

class ProductB implements Product{
    @Override
    public void method1(){
        System.out.println("productB methodB");
    }
}
//将每个产品的具体的实现放在工厂中
class SimpleFactory{
    public static Product createProduct(int type){
        if (type == 0){
            return new ProductA();
        }else if (type==1){
            return  new ProductB();
        }else {
            return null;
        }
    }
}


class Application{
    private Product createProduct(int type){
        return SimpleFactory.createProduct(type);
    }

    Product getObj(int type){
        return createProduct(type);
    }

}