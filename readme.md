# Welcome to ProjectHTTP!
### A Project by Marc
## For Java Training

#### Overview of functionality:
1. Retrieve data values from an app such as Postman
2. The data will be processessed in the app's service classes
3. Processed data will be sent to SQL format
- Create sellers/products
- Delete sellers/products
- Update products

### Known Issues!
- When starting program with sellers inserted in Tables.sql, when attempting to add a seller with a same name in Postman it will appear to post but upon retrieving the sellers again it keeps the original inserted seller
  - Potential seller long ID in seller service conflicting with seller int ID from DAO

### To do:
- Refactor seller ID from long to int to match service to DAO