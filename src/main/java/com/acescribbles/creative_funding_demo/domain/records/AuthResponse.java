package com.acescribbles.creative_funding_demo.domain.records;

import java.time.Instant;

public record AuthResponse(
		long id,
		String email,
		String displayName,
		String handle,
		Instant createdAt,
		Instant lastModifiedAt
) { }
