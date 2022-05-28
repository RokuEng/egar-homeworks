# Hibernate, Автор: Стенькин Александр

***

Все виды связей реализованы, поэтому не буду заострять на них внимание.

Интерфейс **Dao** является расширенным API для работы с Hibernate и содержит следующие нереализованные методы:

* `Optional<E> findById(ID id);`
* `List<E> findAll();`

Дефолтный метод **E save(E e)** (он не предполагает изменений в наследниках для работы Open-Closed Principal, этот метод имеет более сложную проверку сущностей и может принять не только **TRANSIENT** и **PERSISTENT**, но и **DETACHED** сущности)

***

Абстрактные методы описанные выше должны использовать уже существующие: 
* `findById(Class<E> clazz, ID id);`
* `findAll(Class<E> clazz);`

***

Реализованы методы для поиска по критериям и с выгрузкой с помощью **EntityGraph**:

* `List<E> findByCriteria(Class<E> clazz, TriConsumer<CriteriaBuilder, CriteriaQuery<E>, Root<E>> function)`
* `Optional<E> findByIdEntityGraph(ID id, Class<E> clazz, Consumer<EntityGraph<E>> consumer)`
* `List<E> findAllEntityGraph(Class<E> clazz, Consumer<EntityGraph<E>> consumer)`

Примеры использования этих методов есть в имплементациях интерфейса **Dao**, но суть такова: 

нужно написать **лямбда** выражение, в котором задать критерии поиска `return findByCriteria(Person.class,(cb, query, root) -> query.where(
    cb.greaterThan(root.get("age"),age)
));`


или задать аттрибуты/сабграфы для жадной выгрузки `return findByIdEntityGraph(id, State.class, eg -> 
    eg.addAttributeNodes("employees")
);`

***

В том случае, если столь декларативного подхода будет недостаточно и придётся использовать более точечный подход, выше описанные методы работают с помощью более низкоуровневых:

`List<E> useCriteriaQuery(Class<E> clazz, Map<String, Object> hint, TriConsumer<CriteriaBuilder, CriteriaQuery<E>, Root<E>> function) {` где **hint** - карта, в которой хранится **EntityGraph** и тип его выгрузки, получить его можно с помощью метода `Map<String, Object> getEntityGraphProperties(GraphType type, Class<E> clazz, Consumer<EntityGraph<E>> function)`

***

Для прочих низкоуровневых действий есть методы:

* `<T> T useTransaction(Function<EntityManager, T> function)` метод начнёт транзакцию и закроет её после выполнения лямбда выражения
* `<T> T useEntityManager(Function<EntityManager, T> function)` метод является обёрткой для получения **EntityManagerFactory** и имеет полиморфную версию, с возможностью указать закроется ли **EntityManager** после выполнения лямбда выражения или нет

