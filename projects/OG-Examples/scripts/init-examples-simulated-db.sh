#!/bin/sh
SCRIPTDIR="$(dirname "$0")"

echo "### Creating empty database"

${SCRIPTDIR}/run-tool.sh --chdirtoinstallation \
  -Dlogback.configurationFile=jetty-logback.xml \
  com.opengamma.util.test.DbTool \
  -jdbcUrl jdbc:hsqldb:file:data/masterdb/hsqldb/example-db \
  -database og-financial \
  -user "OpenGamma" \
  -password "OpenGamma" \
  -drop true \
  -create true \
  -createtables true

${SCRIPTDIR}/run-tool.sh --chdirtoinstallation \
  -Dlogback.configurationFile=jetty-logback.xml \
  com.opengamma.util.test.DbTool \
  -jdbcUrl jdbc:hsqldb:file:data/userdb/hsqldb/og-fin-user \
  -database og-financial \
  -user "OpenGamma" \
  -password "OpenGamma" \
  -drop true \
  -create true \
  -createtables true

echo "### Adding example data"

${SCRIPTDIR}/run-tool.sh --chdirtoinstallation \
  -Dlogback.configurationFile=jetty-logback.xml \
  com.opengamma.examples.simulated.tool.ExampleDatabasePopulator

echo "### Completed"
