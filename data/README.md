**Naming Convention**
- IN - intake
- SQ - Sequence
- IX - index
- UQ - Unique
- PK - Primary Key
- FK - Foreign Key

**Command Line**

    mvn sql:execute@reset_drop_all
    mvn sql:execute@create_tables_seed
    
**Maven Settings**


    <!-- <USERHOME>/.m2/settings.xml -->
    <servers>
        <server>
            <id>postgres-dev</id>
            <username>postgres</username>
            <password>abc123</password>
        </server>
    </servers>
    
    