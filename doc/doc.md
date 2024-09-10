# Alarmservice
Example demo app

### Schedules
Schedules indicate whether or not to an alarm needs to be created in case of an incoming event.

```mermaid
gantt
    title Schedules
    dateFormat HH:mm
    
    section Room 1
        Schedule 1 : 00:00, 6h
        Schedule 2 : 22:00, 2h

    section Room 2
        Schedule 3 : 00:00, 8h
        Schedule 4 : 18:00, 6h
    
    section Room 3
        Schedule 4 : 08:00, 12h
    
    section ...
        ... : 00:00, 24h

    axisFormat %H:%M
```

### Entities
Our DB holds entries for __Schedules__, __Rooms__ and __Alarms__

```mermaid
erDiagram

    SCHEDULE {
        int id PK
        int roomId FK
        int begin
        int end
        int daysOfWeekMask
    }

    ROOM {
        int id PK
        string name
    }

    ALARM {
        int id PK
        int roomId FK
        date timestamp
        string reason
        boolean acknowledged
    }

    SCHEDULE ||..o{ ROOM : valid-for
    ALARM ||--o{ ROOM : has-been-created-for
    
```

# Class diagramm
```mermaid
classDiagram
    %% Example showing the use of cardinalities

    %% Defining the classes
    class Room {
        +int id
        +String name

    }
    class Event {
        +String eventType
        +int roomId
        +date timestamp
    }
    class Alarm {
        +int id
        +int roomId
        +date timestamp
        +string reason
        +boolean acknowledged
    }
    class Schedule {
        +int id
        +int begin
        +int end
        +int daysOfWeekMask
    }

    class AlarmService {

    }

    %% Defining the relationships with cardinalities
    Event "1" --> "0..1" Alarm : causes
    Room "1" --> "0..*" Schedule : has assigned
    Alarm "1" --> "1" Room : has been created for

    AlarmService --> Event : consumes and evaluates
```

### Flow
```mermaid
flowchart TD
  classDef redNode fill:#D50000,color:#000000;
  classDef pinkNode fill:#E1BEE7,color:#000000;
  classDef yellowNode fill:#FFF9C4,color:#000000;
  classDef blackNode fill:#000000,stroke:#FFD600,stroke-width:4px,stroke-dasharray: 0,color:#FFFFFF;
  classDef greenNode fill:#00F840,color:#000000;
  classDef reminderNode stroke:#FFD600,stroke-width:4px,stroke-dasharray: 0,fill:#000000,color:#FFFFFF;
  classDef blueSubgraph fill:#BBDEFB;

  Event(("Event is <br>POSTed")):::redNode
  RoomExists{"Room exists?"}:::blackNode
  ScheduleMatches{"One of the room's <br>schedule matches?"}:::blackNode
  CreateAlarm["Alarm is created in DB"]
  ResponseOk["OK response"]
  ResponseNotFound["NOT_FOUND response"]
  End((End))

  Event --> RoomExists
  RoomExists -- yes --> ScheduleMatches
  ScheduleMatches -- yes --> CreateAlarm
  ScheduleMatches -- no --> ResponseOk
  RoomExists -- no --> ResponseNotFound

  CreateAlarm --> ResponseOk

  ResponseOk --> End
  ResponseNotFound --> End

```
