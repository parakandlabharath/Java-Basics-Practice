# 03 — Scalability and Reliability

These notes summarise the introductory concepts practised in the learning sessions. Each technique solves a particular problem and introduces trade-offs.

## 1. Scalability

Scalability is a system's ability to handle increased workload by adding resources or changing its design.

- **Vertical scaling:** give one machine more CPU, memory or storage. It is simple initially, but has hardware limits and may leave a single point of failure.
- **Horizontal scaling:** add more application instances. It can increase capacity, but requires routing, shared state strategy and operational coordination.

## 2. Load balancing

A load balancer distributes incoming requests across multiple healthy application instances.

```text
             Clients
                |
                v
          Load Balancer
           /    |    \
          v     v     v
       App 1  App 2  App 3
           \    |    /
                v
          Shared database
```

Benefits include spreading traffic and routing around unhealthy instances. Health checks, session handling, capacity and database bottlenecks still need attention.

## 3. Caching

A cache stores frequently used data in a faster location so repeated requests can avoid expensive work.

Example: a course catalogue that changes infrequently may be cached for a short period.

Important questions:

- How long is cached data valid (TTL)?
- How is stale data invalidated after an update?
- What happens on a cache miss or cache outage?
- Is the cached data safe to share between users?

Caching can reduce latency and database load, but invalidation and consistency are challenging.

## 4. Database replication

Replication keeps copies of data on more than one database server.

- **Primary/replica:** writes usually go to the primary; replicas can serve some reads.
- Replication can improve read capacity and support recovery.
- Replication lag may mean a replica briefly returns older data.
- Replication is not the same as a backup: accidental deletion can replicate too.

## 5. Backup and recovery

A backup is a recoverable copy of data kept separately from the active database.

A useful plan defines:

- Backup frequency and retention.
- Encryption and access restrictions.
- Recovery Point Objective (RPO): acceptable amount of data loss measured in time.
- Recovery Time Objective (RTO): target time to restore service.
- Regular restore tests; an untested backup is not a proven recovery plan.

## 6. Sharding

Sharding splits a large dataset across multiple database partitions (shards), often by a shard key such as customer or tenant ID.

It can distribute storage and query load, but makes joins, transactions, rebalancing and cross-shard queries more complex. It is usually considered after simpler database and indexing improvements are insufficient.

## 7. Failover and high availability

Failover moves work to a healthy standby component when the active component fails.

High availability is a system-level goal; it is not achieved by replication alone. Consider redundant application instances, database failover, health checks, backups, monitoring, tested recovery procedures and failure modes across regions or zones where appropriate.

## 8. Putting the ideas together

For a growing Student Management System, a reasonable progression might be:

1. Start with a well-structured application and a correctly designed relational database.
2. Add indexes based on measured slow queries.
3. Add application instances behind a load balancer when traffic requires it.
4. Cache safe, frequently-read data where the freshness rules are clear.
5. Introduce database replicas if read load or recovery requirements justify them.
6. Maintain independent backups and regularly test restoration.
7. Consider sharding only when scale and access patterns justify the added complexity.

Do not add every technology on day one. Choose based on measured bottlenecks and requirements.

## Interview quick checks

- **Load balancer:** distributes traffic across instances.
- **Cache:** stores reusable data to reduce repeated work.
- **Replication:** maintains copies of data on other database nodes.
- **Backup:** provides a recovery copy, ideally independent of the live system.
- **Sharding:** partitions data across multiple databases/nodes.
- **Failover:** switches service to a healthy alternative after a failure.
- **High availability:** designs the complete system to minimise downtime.
