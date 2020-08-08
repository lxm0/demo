package copyPropertityUtil;

import cn.hutool.core.bean.BeanUtil;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class CopyPropertiesUtilTest {
    public static StringBuilder TimeAll = new StringBuilder();
    public static void main(String[] args)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        PersonDO personDO = new PersonDO();

        personDO.setName("Hollis");
        personDO.setAge(26);
        personDO.setBirthday(new Date());
        personDO.setId(1);

        CopyPropertiesUtilTest mapperTest = new CopyPropertiesUtilTest();

        mapperTest.mappingBySpringBeanUtils(personDO, 100);
        mapperTest.mappingBySpringBeanUtils(personDO, 1000);
        mapperTest.mappingBySpringBeanUtils(personDO, 10000);
        mapperTest.mappingBySpringBeanUtils(personDO, 100000);
        mapperTest.mappingBySpringBeanUtils(personDO, 1000000);
        mapperTest.mappingByCglibBeanCopier(personDO, 100);
        mapperTest.mappingByCglibBeanCopier(personDO, 1000);
        mapperTest.mappingByCglibBeanCopier(personDO, 10000);
        mapperTest.mappingByCglibBeanCopier(personDO, 100000);
        mapperTest.mappingByCglibBeanCopier(personDO, 1000000);
        mapperTest.mappingByApachePropertyUtils(personDO, 100);
        mapperTest.mappingByApachePropertyUtils(personDO, 1000);
        mapperTest.mappingByApachePropertyUtils(personDO, 10000);
        mapperTest.mappingByApachePropertyUtils(personDO, 100000);
        mapperTest.mappingByApachePropertyUtils(personDO, 1000000);
        mapperTest.mappingByApacheBeanUtils(personDO, 100);
        mapperTest.mappingByApacheBeanUtils(personDO, 1000);
        mapperTest.mappingByApacheBeanUtils(personDO, 10000);
        mapperTest.mappingByApacheBeanUtils(personDO, 100000);
        mapperTest.mappingByApacheBeanUtils(personDO, 1000000);
        mapperTest.mappingByHuToolPropertyUtils(personDO, 100);
        mapperTest.mappingByHuToolPropertyUtils(personDO, 1000);
        mapperTest.mappingByHuToolPropertyUtils(personDO, 10000);
        mapperTest.mappingByHuToolPropertyUtils(personDO, 100000);
        mapperTest.mappingByHuToolPropertyUtils(personDO, 1000000);
        System.out.print("---------------------------------------");
        System.out.print(TimeAll);
    }

    /**
     * 使用Cglib BeanCopier进行属性拷贝
     * @param personDO
     * @param times
     */
    private void mappingByCglibBeanCopier(PersonDO personDO, int times) {
        StopWatch stopwatch = new StopWatch();

        stopwatch.start();

        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            BeanCopier copier = BeanCopier.create(PersonDO.class, PersonDTO.class, false);
            copier.copy(personDO, personDTO, null);
        }

        stopwatch.stop();
        TimeAll.append("mappingByCglibBeanCopier cost :" + stopwatch.getTotalTimeMillis()).append("\n");
        System.out.println("mappingByCglibBeanCopier cost :" + stopwatch.getTotalTimeMillis());
    }

    /**\
     * 使用Spring BeanUtils进行属性拷贝
     * @param personDO
     * @param times
     */
    private void mappingBySpringBeanUtils(PersonDO personDO, int times) {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            org.springframework.beans.BeanUtils.copyProperties(personDO, personDTO);
        }

        stopwatch.stop();
        TimeAll.append("mappingBySpringBeanUtils cost :" + stopwatch.getTotalTimeMillis()).append("\n");
        System.out.println("mappingBySpringBeanUtils cost :" + stopwatch.getTotalTimeMillis());
    }

    /**
     * 使用Apache BeanUtils进行属性拷贝
     * @param personDO
     * @param times
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void mappingByApacheBeanUtils(PersonDO personDO, int times)
            throws InvocationTargetException, IllegalAccessException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            BeanUtils.copyProperties(personDTO, personDO);
        }
        stopwatch.stop();
        TimeAll.append("mappingByApacheBeanUtils cost :" + stopwatch.getTotalTimeMillis()).append("\n");
        System.out.println("mappingByApacheBeanUtils cost :" + stopwatch.getTotalTimeMillis());
    }

    /**
     * 使用Apache PropertyUtils进行属性拷贝
     * @param personDO
     * @param times
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private void mappingByApachePropertyUtils(PersonDO personDO, int times)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            PropertyUtils.copyProperties(personDTO, personDO);
        }
        stopwatch.stop();
        TimeAll.append("mappingByApachePropertyUtils cost :" + stopwatch.getTotalTimeMillis()).append("\n");
        System.out.println("mappingByApachePropertyUtils cost :" + stopwatch.getTotalTimeMillis());
    }

    private void mappingByHuToolPropertyUtils(PersonDO personDO, int times) {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            BeanUtil.copyProperties(personDTO, personDO);
        }
        stopwatch.stop();
        TimeAll.append("mappingByHuToolPropertyUtils cost :" + stopwatch.getTotalTimeMillis()).append("\n");
        System.out.println("mappingByHuToolPropertyUtils cost :" + stopwatch.getTotalTimeMillis());
    }
}
