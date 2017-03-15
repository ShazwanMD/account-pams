**Naming Convention**
- IN - intake
- SQ - Sequence
- IX - index
- UQ - Unique
- PK - Primary Key
- FK - Foreign Key

**Command Line**

    mvn sql:execute@db-drop
    mvn sql:execute@db-seed
    
**Maven Settings**


    <!-- <USERHOME>/.m2/settings.xml -->
    <servers>
        <server>
            <id>postgres-dev</id>
            <username>postgres</username>
            <password>abc123</password>
        </server>
    </servers>
    
    